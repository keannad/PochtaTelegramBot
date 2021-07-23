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
import ru.bletenkov.pochtabot.services.PackageService;

import javax.script.ScriptEngine;

public class DeleteCommand implements IBotCommand {

    private static final String logTAG = CommandsEnum.DELETE.toString();
    private String commandName = "delete";
    private String description = "Delete parcel from tracking";

    private PackageService packageService;

    public DeleteCommand(PackageService packageService) {
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

        String messageString = "Delete parcel tracking number from database \n /delete [Number1] [Number2] ...";
        if(strings.length != 0){
            for(String code : strings) {
                packageService.deleteByCode(code);
            }
            messageString = "Tracking numbers has been deleted";
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
