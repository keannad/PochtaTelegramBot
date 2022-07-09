package ru.bletenkov.pochtabot.command;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.bletenkov.pochtabot.dto.russianpost.response.language.LanguageData;
import ru.bletenkov.pochtabot.dto.user.UserDto;
import ru.bletenkov.pochtabot.service.PostConnectService;
import ru.bletenkov.pochtabot.service.PostRequestService;
import ru.bletenkov.pochtabot.service.UserService;

import javax.xml.soap.SOAPMessage;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LanguageCommand extends AbstractCommand{

    private final UserService userService;

    private final PostConnectService postConnectService;
    private final PostRequestService postRequestService;

    public LanguageCommand(UserService userService, PostConnectService postConnectService,
                           PostRequestService postRequestService) {
        super("language", "Get available languages");

        this.userService = userService;
        this.postConnectService = postConnectService;
        this.postRequestService = postRequestService;
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

        messageString.append("Current language: <b>").append(userDto.getLanguage()).append("</b>\n\n");

        SOAPMessage serviceRequest = postRequestService.generateLanguageRequest();
        SOAPMessage serviceResponse = postConnectService.getSingleResponse(serviceRequest);
        List<LanguageData.Language> response = postRequestService.parseLanguageRequest(serviceResponse);

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardList = new ArrayList<>();

        List<InlineKeyboardButton> keyboardRow = new ArrayList<>();
        for (LanguageData.Language lang : response) {
            messageString.append(response.indexOf(lang) + 1).append(") <i>").append(lang.getName())
                    .append("</i> ➝ <b>").append(lang.getCode()).append("</b>\n");

            keyboardRow.add(InlineKeyboardButton.builder()
                    .text(lang.getName())
                    .callbackData("/setLanguage " + lang.getCode()).build());

            if (keyboardRow.size() > 4) {
                keyboardList.add(keyboardRow);
                keyboardRow = new ArrayList<>();
            }
        }

        if (!keyboardRow.isEmpty()) {
            keyboardList.add(keyboardRow);
        }
        keyboard.setKeyboard(keyboardList);

        sendMessage(absSender, chatId, messageString.toString());
    }

}
