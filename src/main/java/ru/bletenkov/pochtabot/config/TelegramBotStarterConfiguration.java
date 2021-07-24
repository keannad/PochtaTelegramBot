package ru.bletenkov.pochtabot.config;
/*
    Created by IntelliJ IDEA
    @author:     Bletenkov Kirill aka Keannad
    @date:       19.07.2021
    @project:    PochtaTelegramBot
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.bletenkov.pochtabot.services.PackageService;
import ru.bletenkov.pochtabot.services.UserService;

@Configuration
public class TelegramBotStarterConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        return new TelegramBotsApi(DefaultBotSession.class);
    }

    @Bean
    public UserService userService(){
        return new UserService();
    }

    @Bean
    public PackageService packageService(){
        return new PackageService();
    }

    @Bean
    public TelegramBotInitializer telegramBotInitializer(TelegramBotsApi telegramBotsApi,
                                                         UserService userService,
                                                         PackageService packageService) throws TelegramApiException {
        return new TelegramBotInitializer(telegramBotsApi(), userService(), packageService());
    }
}
