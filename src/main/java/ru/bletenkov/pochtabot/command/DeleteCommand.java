package ru.bletenkov.pochtabot.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bletenkov.pochtabot.service.impl.PackageServiceImpl;

@RequiredArgsConstructor
@Slf4j
public class DeleteCommand implements IBotCommand {

    private final String commandName = "delete";
    private final String description = "Delete parcel from tracking";

    private final PackageServiceImpl packageServiceImpl;

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
                packageServiceImpl.deleteByUserIdAndCode(message.getChatId(), code);
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
