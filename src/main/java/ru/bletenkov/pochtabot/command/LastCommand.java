package ru.bletenkov.pochtabot.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
@RequiredArgsConstructor
@Component
public class LastCommand extends AbstractCommand{

    private final String commandName = "last";
    private final String description = "Show last states of tracking parcels";

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
        sendMessage(absSender, chatId, description);
    }
}
