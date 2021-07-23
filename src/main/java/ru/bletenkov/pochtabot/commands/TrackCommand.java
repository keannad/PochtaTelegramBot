package ru.bletenkov.pochtabot.commands;
/*
    Created by IntelliJ IDEA
    @author:     Bletenkov Kirill aka Keannad
    @date:       19.07.2021
    @project:    PochtaTelegramBot
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bletenkov.pochtabot.enums.CommandsEnum;
import ru.bletenkov.pochtabot.services.PostSOAPSingleService;
import ru.bletenkov.pochtabot.services.PostService;

public class TrackCommand implements IBotCommand {

    private static final String logTAG = CommandsEnum.LAST.toString();
    private String commandName = "track";
    private String description = "Track one parcel";

    private PostService service;

    public TrackCommand(PostService service) {
        this.service = service;
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

        String messageString = "";
        if(strings.length == 0){
                messageString = "Track single parcel by tracking number \n /track [Number1]";
        }else{
            if(strings.length > 1){
                messageString = "You can track only single parcel per request \n /track [Number1] \n";
            }

            messageString += service.getOperationHistory(strings[0]);
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(messageString);

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
