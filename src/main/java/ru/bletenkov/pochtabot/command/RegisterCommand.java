package ru.bletenkov.pochtabot.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.bletenkov.pochtabot.model.User;
import ru.bletenkov.pochtabot.service.UserService;

@Slf4j
@RequiredArgsConstructor
@Component
public class RegisterCommand extends AbstractCommand {

    private final String commandName = "register";
    private final String description = "Register user to get auto updates";

    private final UserService userService;

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

        User user = userService.getUserByChatId(chatId);
        StringBuilder messageString = new StringBuilder();
        if(user == null){
            user = new User();
            user.setChatId(message.getChatId());
            userService.saveUserModel(user);
            messageString.append("User ")
                    .append(message.getFrom().getUserName())
                    .append(" was saved to database");
        }else{
            messageString.append("User ")
                    .append(message.getFrom().getUserName())
                    .append(" is already saved to database");
        }

        sendMessage(absSender, chatId, messageString.toString());
    }

}
