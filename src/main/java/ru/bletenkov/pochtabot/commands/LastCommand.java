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

public class LastCommand implements IBotCommand {

    private static final String logTAG = CommandsEnum.LAST.toString();
    private final String commandName = "last";
    private final String description = "Show last states of tracking parcels";

    private final PackageService packageService;

    public LastCommand(PackageService packageService) {
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

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(description);

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
