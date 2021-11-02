package ru.bletenkov.pochtabot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bletenkov.pochtabot.command.AddCommand;
import ru.bletenkov.pochtabot.command.DeleteCommand;
import ru.bletenkov.pochtabot.command.LastCommand;
import ru.bletenkov.pochtabot.command.RegisterCommand;
import ru.bletenkov.pochtabot.command.StartCommand;
import ru.bletenkov.pochtabot.command.TrackCommand;
import ru.bletenkov.pochtabot.service.PackageService;
import ru.bletenkov.pochtabot.service.PostService;
import ru.bletenkov.pochtabot.service.UserService;

import java.util.List;
import java.util.Properties;

@Component
@Slf4j
public class PochtaTelegamBot extends TelegramLongPollingCommandBot {

    private String bot_name;
    private String bot_token;

    private final UserService userService;
    private final PackageService packageService;
    private final PostService postService;

    private final AddCommand addCommand;
    private final DeleteCommand deleteCommand;
    private final LastCommand lastCommand;
    private final RegisterCommand registerCommand;
    private final StartCommand startCommand;
    private final TrackCommand trackCommand;

    public PochtaTelegamBot(UserService userService,
                            PackageService packageService,
                            PostService postService,
                            AddCommand addCommand,
                            DeleteCommand deleteCommand,
                            LastCommand lastCommand,
                            RegisterCommand registerCommand,
                            StartCommand startCommand,
                            TrackCommand trackCommand) {
        super();

        this.userService = userService;
        this.packageService = packageService;
        this.postService = postService;
        this.addCommand = addCommand;
        this.deleteCommand = deleteCommand;
        this.lastCommand = lastCommand;
        this.registerCommand = registerCommand;
        this.startCommand = startCommand;
        this.trackCommand = trackCommand;

        try {

            ClassLoader loader = Thread.currentThread().getContextClassLoader();

            Properties appProps = new Properties();
            appProps.load(loader.getResourceAsStream("bot.properties"));
            this.bot_name = appProps.getProperty("botname");
            this.bot_token = appProps.getProperty("bottoken");

        }catch (Exception ex){
            ex.printStackTrace();
        }

        register(startCommand);
        register(addCommand);
        register(deleteCommand);
        register(lastCommand);
        register(registerCommand);
        register(trackCommand);
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

    @Override
    public String getBotUsername() {
        return bot_name;
    }

    @Override
    public String getBotToken() {
        return bot_token;
    }
}
