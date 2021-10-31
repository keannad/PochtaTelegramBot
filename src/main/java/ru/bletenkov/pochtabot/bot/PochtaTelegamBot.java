package ru.bletenkov.pochtabot.bot;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bletenkov.pochtabot.command.*;
import ru.bletenkov.pochtabot.service.impl.PackageServiceImpl;
import ru.bletenkov.pochtabot.service.impl.PostSOAPSingleService;
import ru.bletenkov.pochtabot.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Properties;

public class PochtaTelegamBot extends TelegramLongPollingCommandBot {

    private String bot_name;
    private String bot_token;

    private final UserServiceImpl userService;
    private final PackageServiceImpl packageServiceImpl;

    public PochtaTelegamBot(UserServiceImpl userService,
                            PackageServiceImpl packageServiceImpl) {
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
        this.packageServiceImpl = packageServiceImpl;

        register(new StartCommand());
        register(new AddCommand(packageServiceImpl));
        register(new DeleteCommand(packageServiceImpl));
        register(new LastCommand(packageServiceImpl));
        register(new RegisterCommand(userService));
        register(new TrackCommand(new PostSOAPSingleService(), userService, packageServiceImpl));
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return bot_name;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public String getBotToken() {
        return null;
    }
}
