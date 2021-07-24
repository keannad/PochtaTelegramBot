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
import ru.bletenkov.pochtabot.models.PackageModel;
import ru.bletenkov.pochtabot.services.PackageService;

public class AddCommand implements IBotCommand {

    public static final String logTAG = CommandsEnum.ADD.toString();
    private final String commandName = "add";
    private final String description = "Add parcel to tracking";

    private final PackageService packageService;

    public AddCommand(PackageService packageService) {
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
        StringBuilder messageString = new StringBuilder("Add new parcel tracking number to database \n /add [Number1] [Number2] ...");
        if(strings.length != 0){
            messageString = new StringBuilder("New parcel code ");
            int counter = 0;

            for(String code: strings){
                PackageModel pack = packageService.getByCodeAndUserId(code, message.getChatId());

                if (pack == null) {
                    pack = new PackageModel();
                    pack.setUserId(message.getChatId());
                    pack.setCode(code);
                    packageService.savePackage(pack);
                    messageString.append(code).append(", ");
                    counter++;
                }
            }

            if (counter == 0){
                messageString = new StringBuilder("No one new parcel codes ");
            }else{
                messageString = new StringBuilder(messageString.substring(0, messageString.length() - 2));
            }
            messageString.append("was added to Database");
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(messageString.toString());

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
