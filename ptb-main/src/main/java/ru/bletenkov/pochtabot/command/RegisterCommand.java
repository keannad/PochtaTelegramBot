package ru.bletenkov.pochtabot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.bletenkov.pochtabot.dto.user.UserDto;
import ru.bletenkov.pochtabot.service.UserService;

@Slf4j
@Component
public class RegisterCommand extends AbstractCommand{

    private final UserService userService;

    public RegisterCommand(UserService userService){
        super("register", "Register user to get auto updates");

        this.userService = userService;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        Long chatId = chat.getId();

        UserDto userDto = userService.getUserByChatId(chatId);
        StringBuilder messageString = new StringBuilder();
        if(userDto == null){
            UserDto dto = new UserDto();
            dto.setChatId(chatId);
            dto.setIsAdmin(Boolean.FALSE);

            userDto = userService.save(dto);
            messageString.append("User ").append(user.getUserName())
                    .append("( chatId = ").append(chatId).append(" )")
                    .append(" was saved to database")
                    .append("( id = ").append(userDto.getId()).append(" )");
        }else{
            messageString.append("User ").append(user.getUserName())
                    .append("( chatId = ").append(chatId).append(" )")
                    .append(" is already saved to database")
                    .append("( id = ").append(userDto.getId()).append(" )");
        }

        sendMessage(absSender, chatId, messageString.toString());
    }

}
