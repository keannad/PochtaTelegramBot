package ru.bletenkov.pochtabot.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bletenkov.pochtabot.model.User;
import ru.bletenkov.pochtabot.service.impl.UserServiceImpl;

@RequiredArgsConstructor
@Slf4j
public class RegisterCommand implements IBotCommand {

    private final String commandName = "register";
    private final String description = "Register user to get auto updates";

    private final UserServiceImpl userService;

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

        User user = userService.getUserByChatId(message.getChatId());
        String messageString;
        if(user == null){
            user = new User();
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
