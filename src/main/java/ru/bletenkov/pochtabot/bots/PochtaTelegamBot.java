package ru.bletenkov.pochtabot.bots;
/*
    Created by IntelliJ IDEA
    @author:     Bletenkov Kirill aka Keannad
    @date:       19.07.2021
    @project:    PochtaTelegramBot
*/

import com.jayway.jsonpath.internal.function.json.Append;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bletenkov.pochtabot.commands.*;
import ru.bletenkov.pochtabot.repos.UserRepository;
import ru.bletenkov.pochtabot.services.PackageService;
import ru.bletenkov.pochtabot.services.PostSOAPSingleService;
import ru.bletenkov.pochtabot.services.UserService;

import java.util.List;

public class PochtaTelegamBot extends TelegramLongPollingCommandBot {

    private final String bot_name = "RussianPostTelegramBot";
    private final String bot_token = "1927863991:AAFEFgSDNBNP7gmAN2SJ1LRoozRtlkDqHeM";

    private final UserService userService;
    private final PackageService packageService;

    public PochtaTelegamBot(UserService userService,
                            PackageService packageService) {
        super();

        this.userService = userService;
        this.packageService = packageService;

        register(new StartCommand());
        register(new AddCommand(packageService));
        register(new DeleteCommand(packageService));
        register(new LastCommand(packageService));
        register(new RegisterCommand(userService));
        register(new TrackCommand(new PostSOAPSingleService()));
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
