package ru.bletenkov.pochtabot.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.bletenkov.pochtabot.service.PackageService;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeleteCommand extends AbstractCommand{

    private final String commandName = "delete";
    private final String description = "Delete parcel from tracking";

    private final PackageService packageService;

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

        StringBuilder messageString = new StringBuilder("Delete parcel tracking number from database ")
                .append("\n /delete [Number1] [Number2] ...");
        Long chatId = message.getChatId();

        if(strings.length != 0){
            for(String code : strings) {
                packageService.deleteByUserIdAndCode(chatId, code);
            }
            messageString = new StringBuilder("Tracking numbers has been deleted");
        }

        sendMessage(absSender, chatId, messageString.toString());
    }
}
