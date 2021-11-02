package ru.bletenkov.pochtabot.command;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class AbstractCommand implements IBotCommand {

    @Override
    public abstract String getCommandIdentifier();

    @Override
    public abstract String getDescription();

    @Override
    public abstract void processMessage(AbsSender absSender, Message message, String[] strings);

    public void sendMessage(AbsSender absSender, Long chatId, String message){

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(message);

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
