package ru.bletenkov.pochtabot.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bletenkov.pochtabot.model.HistoryRecord;
import ru.bletenkov.pochtabot.model.MailPackage;
import ru.bletenkov.pochtabot.service.impl.PackageServiceImpl;
import ru.bletenkov.pochtabot.service.PostService;
import ru.bletenkov.pochtabot.service.impl.UserServiceImpl;

import java.util.ArrayList;

@RequiredArgsConstructor
@Slf4j
public class TrackCommand implements IBotCommand {

    private final String commandName = "track";
    private final String description = "Track one parcel";

    private final PostService service;
    private final UserServiceImpl userService;
    private final PackageServiceImpl packageServiceImpl;

    @Override
    public String getCommandIdentifier() {
        return commandName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] strings) {

        StringBuilder messageString = new StringBuilder();
        if(strings.length == 0){
                messageString.append("Track single parcel by tracking number \n /track [Number1]");
        }else{
            if(strings.length > 1){
                messageString.append("You can track only single parcel per request \n /track [Number1] \n");
            }

            String packageCode = strings[0];
            ArrayList<HistoryRecord>  historyRecords = service.getOperationHistory(packageCode);
            int counter = 1;
            for (HistoryRecord record : historyRecords){
                messageString.append(String.format("|%3d|%-32s|%16s|",
                        counter++,
                        record.getRecordAttrName().isEmpty() ? record.getRecordTypeName() : record.getRecordAttrName(),
                        record.getOperDateString()));
                messageString.append("\n");
            }

            if (userService.isRegistered(message.getChatId())){
                HistoryRecord lastState = historyRecords.get(historyRecords.size() - 1);
                MailPackage model = packageServiceImpl.getByCodeAndUserId(message.getChatId(), packageCode);
                if (model != null){
                    model.setDead(lastState.isLast());
                    packageServiceImpl.save(model);
                }
            }
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(messageString.toString());

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
