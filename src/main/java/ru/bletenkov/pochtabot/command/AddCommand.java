package ru.bletenkov.pochtabot.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.bletenkov.pochtabot.model.MailPackage;
import ru.bletenkov.pochtabot.model.User;
import ru.bletenkov.pochtabot.service.PackageService;
import ru.bletenkov.pochtabot.service.UserService;

@Slf4j
@RequiredArgsConstructor
@Component
public class AddCommand extends AbstractCommand{

    private final String commandName = "add";
    private final String description = "Add parcel to tracking";

    private final UserService userService;
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
        Long chatId = message.getChatId();

        if(strings.length != 0){

            User user = userService.getUserByChatId(chatId);
            if (user == null){
                messageString = new StringBuilder("You are not registered. \nYou can't add parcels.");
                sendMessage(absSender, chatId, messageString.toString());
                return;
            }

            messageString = new StringBuilder("New parcel code ");
            int counter = 0;

            for(String code: strings){
                MailPackage pack = packageService.getByCodeAndUserId(message.getChatId(), code);

                if (pack == null) {
                    pack = new MailPackage();
                    pack.setUser(user);
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

        sendMessage(absSender, chatId, messageString.toString());
    }
}
