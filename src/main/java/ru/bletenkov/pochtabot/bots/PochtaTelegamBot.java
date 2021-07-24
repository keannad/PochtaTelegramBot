package ru.bletenkov.pochtabot.bots;
/*
    Created by IntelliJ IDEA
    @author:     Bletenkov Kirill aka Keannad
    @date:       19.07.2021
    @project:    PochtaTelegramBot
*/

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bletenkov.pochtabot.commands.*;
import ru.bletenkov.pochtabot.services.PackageService;
import ru.bletenkov.pochtabot.services.PostSOAPSingleService;
import ru.bletenkov.pochtabot.services.UserService;

import java.util.List;
import java.util.Properties;

public class PochtaTelegamBot extends TelegramLongPollingCommandBot {

    private String bot_name;
    private String bot_token;

    private final UserService userService;
    private final PackageService packageService;

    public PochtaTelegamBot(UserService userService,
                            PackageService packageService) {
        super();

        try {

            ClassLoader loader = Thread.currentThread().getContextClassLoader();

            Properties appProps = new Properties();
            appProps.load(loader.getResourceAsStream("bot.properties"));
            this.bot_name = appProps.getProperty("botname");
            this.bot_token = appProps.getProperty("bottoken");

        }catch (Exception ex){

            ex.printStackTrace();

        }

        this.userService = userService;
        this.packageService = packageService;

        register(new StartCommand());
        register(new AddCommand(packageService));
        register(new DeleteCommand(packageService));
        register(new LastCommand(packageService));
        register(new RegisterCommand(userService));
        register(new TrackCommand(new PostSOAPSingleService(), userService, packageService));
    }

    @Override
    public String getBotUsername() {
        return bot_name;
    }

    @Override
    public String getBotToken() {
        return bot_token;
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public void processNonCommandUpdate(Update update) {

    }

    @Override
    public void onRegister() {
        super.onRegister();
    }
}
