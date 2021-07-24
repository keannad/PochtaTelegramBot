package ru.bletenkov.pochtabot.commands;
/*
    Created by IntelliJ IDEA
    @author:     Bletenkov Kirill aka Keannad
    @date:       19.07.2021
    @project:    PochtaTelegramBot
*/

import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bletenkov.pochtabot.enums.CommandsEnum;
import ru.bletenkov.pochtabot.models.HistoryRecord;
import ru.bletenkov.pochtabot.models.PackageModel;
import ru.bletenkov.pochtabot.services.PackageService;
import ru.bletenkov.pochtabot.services.PostService;
import ru.bletenkov.pochtabot.services.UserService;

import java.util.ArrayList;

public class TrackCommand implements IBotCommand {

    private static final String logTAG = CommandsEnum.LAST.toString();
    private final String commandName = "track";
    private final String description = "Track one parcel";

    private final PostService service;
    private final UserService userService;
    private final PackageService packageService;

    public TrackCommand(PostService service,
                        UserService userService,
                        PackageService packageService) {
        this.service = service;
        this.userService = userService;
        this.packageService = packageService;
    }

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
                        record.getOperAttrName().isEmpty() ? record.getOperTypeName() : record.getOperAttrName(),
                        record.getOperDateString()));
                messageString.append("\n");
            }

            if (userService.isRegistered(message.getChatId())){
                HistoryRecord lastState = historyRecords.get(historyRecords.size() - 1);
                PackageModel model = packageService.getByCodeAndUserId(packageCode, message.getChatId());
                if (model != null){
                    model.setLastStateString(lastState.getOperAttrName());
                    model.setLastStateDate(lastState.getOperDateISOString());
                    model.setIsDead(lastState.isLast());
                    packageService.savePackage(model);
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
