package ru.bletenkov.pochtabot.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bletenkov.pochtabot.bot.PochtaTelegamBot;
import ru.bletenkov.pochtabot.service.impl.PackageServiceImpl;
import ru.bletenkov.pochtabot.service.impl.UserServiceImpl;

@RequiredArgsConstructor
@Slf4j
public class TelegramBotInitializer implements InitializingBean {

    private final TelegramBotsApi telegramBotsApi;
    private final UserServiceImpl userService;
    private final PackageServiceImpl packageServiceImpl;

    @Override
    public void afterPropertiesSet(){
        try{
            TelegramLongPollingCommandBot bot = new PochtaTelegamBot(userService, packageServiceImpl);
            telegramBotsApi.registerBot(bot);
        }catch (TelegramApiException ex){
            ex.printStackTrace();
        }
    }
}
