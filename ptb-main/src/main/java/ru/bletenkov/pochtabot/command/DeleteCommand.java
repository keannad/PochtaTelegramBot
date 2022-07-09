package ru.bletenkov.pochtabot.command;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.bletenkov.pochtabot.service.MailPackageService;

@Slf4j
public class DeleteCommand extends AbstractCommand{

    private final MailPackageService mailPackageService;

    public DeleteCommand(MailPackageService mailPackageService) {

        super("delete", "Delete parcel from tracking");

        this.mailPackageService = mailPackageService;

    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        Long chatId = chat.getId();

        StringBuilder messageString = new StringBuilder("Delete parcel tracking number from database ")
                .append("\n /delete [Number1] [Number2] ...");

        if(strings.length != 0){
            for(String code : strings) {
                mailPackageService.deleteByUserIdAndCode(chatId, code);
            }
            messageString = new StringBuilder("Tracking numbers has been deleted");
        }

        sendMessage(absSender, chatId, messageString.toString());
    }
}
