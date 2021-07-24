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
import ru.bletenkov.pochtabot.models.UserModel;
import ru.bletenkov.pochtabot.services.UserService;

public class RegisterCommand implements IBotCommand {

    private static final String logTAG = CommandsEnum.REGISTER.toString();
    private final String commandName = "register";
    private final String description = "Register user to get auto updates";

    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
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

        UserModel user = userService.getUserByChatId(message.getChatId());
        String messageString;
        if(user == null){
            user = new UserModel();
            user.setChatId(message.getChatId());
            userService.saveUserModel(user);
            messageString = "User " + message.getFrom().getUserName() + " was saved to database";
        }else{
            messageString = "User " + message.getFrom().getUserName() + " is already saved to database";
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
