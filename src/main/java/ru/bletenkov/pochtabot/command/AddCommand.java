package ru.bletenkov.pochtabot.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bletenkov.pochtabot.model.MailPackage;
import ru.bletenkov.pochtabot.service.PackageService;

@RequiredArgsConstructor
@Slf4j
public class AddCommand implements IBotCommand {

    private final String commandName = "add";
    private final String description = "Add parcel to tracking";

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
        StringBuilder messageString = new StringBuilder("Add new parcel tracking number to database \n /add [Number1] [Number2] ...");
        if(strings.length != 0){
            messageString = new StringBuilder("New parcel code ");
            int counter = 0;

            for(String code: strings){
                MailPackage pack = packageService.getByCodeAndUserId(message.getChatId(), code);

                if (pack == null) {
                    pack = new MailPackage();
                    //pack.setUser(message.getChatId());
                    pack.setCode(code);
                    packageService.save(pack);
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
