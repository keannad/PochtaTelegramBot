package ru.bletenkov.pochtabot.command;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public abstract class AbstractCommand extends BotCommand {

    AbstractCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    void sendMessage(AbsSender absSender, Long chatId, String message){

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(message);

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    void sendMessageWithKeyboard(AbsSender absSender, Long chatId, String message, ReplyKeyboard keyboard){

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(message);

        sendMessage.setReplyMarkup(keyboard);

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
