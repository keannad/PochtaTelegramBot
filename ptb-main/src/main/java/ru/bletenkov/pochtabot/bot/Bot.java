package ru.bletenkov.pochtabot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bletenkov.pochtabot.command.AddCommand;
import ru.bletenkov.pochtabot.command.DeleteCommand;
import ru.bletenkov.pochtabot.command.LanguageCommand;
import ru.bletenkov.pochtabot.command.LastCommand;
import ru.bletenkov.pochtabot.command.RegisterCommand;
import ru.bletenkov.pochtabot.command.SetLanguageCommand;
import ru.bletenkov.pochtabot.command.StartCommand;
import ru.bletenkov.pochtabot.command.TrackCommand;
import ru.bletenkov.pochtabot.service.HistoryService;
import ru.bletenkov.pochtabot.service.MailPackageService;
import ru.bletenkov.pochtabot.service.PostConnectService;
import ru.bletenkov.pochtabot.service.PostRequestService;
import ru.bletenkov.pochtabot.service.UserService;

import java.util.List;

@Slf4j
@Component
public class Bot extends TelegramLongPollingCommandBot {

    @Value("${telegram.bot.name}")
    private String bot_name;
    @Value("${telegram.bot.token}")
    private String bot_token;

    private final UserService userService;
    private final MailPackageService mailPackageService;
    private final HistoryService historyService;

    private final PostConnectService postConnectService;

    private final PostRequestService postParseService;

    public Bot(UserService userService, MailPackageService mailPackageService, HistoryService historyService,
               PostConnectService postConnectService, PostRequestService postParseService) {
        super();

        this.userService = userService;
        this.mailPackageService = mailPackageService;
        this.historyService = historyService;
        this.postConnectService = postConnectService;
        this.postParseService = postParseService;

        register(new AddCommand(userService, mailPackageService));
        register(new DeleteCommand(mailPackageService));
        register(new LastCommand());
        register(new RegisterCommand(userService));
        register(new StartCommand());
        register(new TrackCommand(userService, mailPackageService, postConnectService, postParseService));
        register(new LanguageCommand(userService, postConnectService, postParseService));
        register(new SetLanguageCommand(userService));
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
