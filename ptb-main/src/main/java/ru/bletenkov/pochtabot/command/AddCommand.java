package ru.bletenkov.pochtabot.command;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.bletenkov.pochtabot.dto.mailpackage.MailPackageDto;
import ru.bletenkov.pochtabot.dto.user.UserDto;
import ru.bletenkov.pochtabot.service.MailPackageService;
import ru.bletenkov.pochtabot.service.UserService;
@Slf4j
public class AddCommand extends AbstractCommand{

    private final UserService userService;

    private final MailPackageService mailPackageService;

    public AddCommand(UserService userService, MailPackageService mailPackageService) {

        super("add", "Add parcel to tracking");

        this.userService = userService;
        this.mailPackageService = mailPackageService;

    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        Long chatId = chat.getId();
        StringBuilder messageString = new StringBuilder("Add new parcel tracking number to database \n /add [Number1] [Number2] ...");

        if(strings.length != 0){

            UserDto userDto = userService.getUserByChatId(chatId);
            if (userDto == null){
                messageString = new StringBuilder("You are not registered. \nYou can't add parcels.");
                sendMessage(absSender, chatId, messageString.toString());
                return;
            }

            messageString = new StringBuilder("New parcel code ");
            int counter = 0;

            for(String code: strings){
                MailPackageDto mailPackage = mailPackageService.getByCodeAndUserId(chatId, code);

                if (mailPackage == null) {
                    MailPackageDto dto = new MailPackageDto();
                    dto.setUserId(userDto.getId());
                    dto.setCode(code);
                    dto.setIsDead(Boolean.FALSE);

                    mailPackageService.save(dto);
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
