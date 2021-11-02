package ru.bletenkov.pochtabot.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.bletenkov.pochtabot.model.HistoryRecord;
import ru.bletenkov.pochtabot.model.MailPackage;
import ru.bletenkov.pochtabot.service.PackageService;
import ru.bletenkov.pochtabot.service.PostService;
import ru.bletenkov.pochtabot.service.UserService;

import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
@Component
public class TrackCommand extends AbstractCommand {

    private final String commandName = "track";
    private final String description = "Track one parcel";

    private final PostService postService;
    private final UserService userService;
    private final PackageService packageService;

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

        Long chatId = message.getChatId();

        StringBuilder messageString = new StringBuilder();
        if(strings.length == 0){
            messageString.append("Track single parcel by tracking number \n /track [Number]");
            sendMessage(absSender, chatId, messageString.toString());
            return;
        }

        if(strings.length > 1){
            messageString.append("You can track only single parcel per request \n /track [Number] \n");
        }

        String packageCode = strings[0];
        ArrayList<HistoryRecord>  historyRecords = postService.getOperationHistory(packageCode);
        int counter = 1;
        for (HistoryRecord record : historyRecords){
            messageString.append(String.format("|%3d|%-32s|%16s|",
                    counter++,
                    record.getRecordAttrName().isEmpty() ? record.getRecordTypeName() : record.getRecordAttrName(),
                    record.getRecordDateString()));
            messageString.append("\n");
        }

        if (userService.isRegistered(message.getChatId())){
            HistoryRecord lastState = historyRecords.get(historyRecords.size() - 1);
            MailPackage model = packageService.getByCodeAndUserId(message.getChatId(), packageCode);
            if (model != null){
                model.setDead(lastState.isLast());
                packageService.save(model);
            }
        }

        sendMessage(absSender, chatId, messageString.toString());
    }
}
