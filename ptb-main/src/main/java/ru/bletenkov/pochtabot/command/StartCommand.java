package ru.bletenkov.pochtabot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
public class StartCommand extends AbstractCommand {

    public StartCommand() {
        super("start", "Bot start command");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        Long chatId = chat.getId();
        sendMessage(absSender, chatId, "Russian Post Telegram Bot");
    }
}
