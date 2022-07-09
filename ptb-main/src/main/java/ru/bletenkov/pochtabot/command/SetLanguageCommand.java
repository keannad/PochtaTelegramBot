package ru.bletenkov.pochtabot.command;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.bletenkov.pochtabot.dto.user.UserDto;
import ru.bletenkov.pochtabot.service.UserService;

@Slf4j
public class SetLanguageCommand extends AbstractCommand{

    private final UserService userService;

    public SetLanguageCommand(UserService userService) {
        super("setLanguage", "Set current user request language");
        this.userService = userService;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        StringBuilder messageString = new StringBuilder();

        Long chatId = chat.getId();
        UserDto userDto = userService.getUserByChatId(chatId);
        if (userDto == null) {
            messageString.append("Select language available only to registered users!");
            sendMessage(absSender, chatId, messageString.toString());
            return;
        }

        userDto.setLanguage(strings[0]);
        userService.save(userDto);

    }
}
