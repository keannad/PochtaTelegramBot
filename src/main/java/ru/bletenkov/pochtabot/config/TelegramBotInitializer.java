package ru.bletenkov.pochtabot.config;
/*
    Created by IntelliJ IDEA
    @author:     Bletenkov Kirill aka Keannad
    @date:       19.07.2021
    @project:    PochtaTelegramBot
*/

import org.springframework.beans.factory.InitializingBean;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bletenkov.pochtabot.bots.PochtaTelegamBot;
import ru.bletenkov.pochtabot.services.PackageService;
import ru.bletenkov.pochtabot.services.UserService;

public class TelegramBotInitializer implements InitializingBean {

    private final UserService userService;
    private final PackageService packageService;
    private final TelegramBotsApi telegramBotsApi;

    public TelegramBotInitializer(TelegramBotsApi telegramBotsApi,
                                  UserService userService,
                                  PackageService packageService){
        this.telegramBotsApi = telegramBotsApi;
        this.userService = userService;
        this.packageService = packageService;
    }

    @Override
    public void afterPropertiesSet(){
        try{
            TelegramLongPollingCommandBot bot = new PochtaTelegamBot(userService, packageService);
            telegramBotsApi.registerBot(bot);
        }catch (TelegramApiException ex){
            ex.printStackTrace();
        }
    }
}
