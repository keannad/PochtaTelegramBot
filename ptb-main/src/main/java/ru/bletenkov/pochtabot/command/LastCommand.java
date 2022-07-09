package ru.bletenkov.pochtabot.command;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
public class LastCommand extends AbstractCommand{

    public LastCommand() {
        super("last", "Show last states of tracking parcels");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        Long chatId = chat.getId();
        sendMessage(absSender, chatId, getDescription());
    }
}
