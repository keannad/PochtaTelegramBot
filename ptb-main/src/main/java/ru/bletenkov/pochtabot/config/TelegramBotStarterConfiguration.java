package ru.bletenkov.pochtabot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.bletenkov.pochtabot.mapper.HistoryMapper;
import ru.bletenkov.pochtabot.mapper.MailPackageMapper;
import ru.bletenkov.pochtabot.mapper.UserMapper;
import ru.bletenkov.pochtabot.repository.HistoryRepository;
import ru.bletenkov.pochtabot.repository.MailPackageRepository;
import ru.bletenkov.pochtabot.repository.UserRepository;
import ru.bletenkov.pochtabot.service.HistoryService;
import ru.bletenkov.pochtabot.service.MailPackageService;
import ru.bletenkov.pochtabot.service.PostConnectService;
import ru.bletenkov.pochtabot.service.UserService;
import ru.bletenkov.pochtabot.service.impl.HistoryServiceImpl;
import ru.bletenkov.pochtabot.service.impl.MailPackageServiceImpl;
import ru.bletenkov.pochtabot.service.impl.PostConnectServiceImpl;
import ru.bletenkov.pochtabot.service.impl.PostSOAPSingleServiceImpl;
import ru.bletenkov.pochtabot.service.impl.UserServiceImpl;

@Configuration
@ComponentScan({
        "ru.bletenkov.pochtabot",
        "ru.bletenkov.pochtabot.service"
})
public class TelegramBotStarterConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        return new TelegramBotsApi(DefaultBotSession.class);
    }

    @Bean
    public UserService getUserService(UserRepository userRepository, UserMapper userMapper) {
        return new UserServiceImpl(userRepository, userMapper);
    }

    @Bean
    public HistoryService getHistoryService(HistoryRepository historyRepository, HistoryMapper historyMapper) {
        return new HistoryServiceImpl(historyRepository, historyMapper);
    }

    @Bean
    public MailPackageService getMailPackageService(MailPackageRepository mailPackageRepository, MailPackageMapper mailPackageMapper) {
        return new MailPackageServiceImpl(mailPackageRepository, mailPackageMapper);
    }

    @Bean
    public PostConnectService getPostConnectService() {
        return new PostConnectServiceImpl();
    }

    @Bean
    public PostSOAPSingleServiceImpl getPostSOAPSingleService() {
        return new PostSOAPSingleServiceImpl();
    }

}
