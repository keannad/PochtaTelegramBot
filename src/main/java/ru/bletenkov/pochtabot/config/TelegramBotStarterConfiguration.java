package ru.bletenkov.pochtabot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.bletenkov.pochtabot.repository.PackageRepository;
import ru.bletenkov.pochtabot.repository.UserRepository;
import ru.bletenkov.pochtabot.service.PackageService;
import ru.bletenkov.pochtabot.service.UserService;
import ru.bletenkov.pochtabot.service.impl.PackageServiceImpl;
import ru.bletenkov.pochtabot.service.impl.UserServiceImpl;

@Configuration
public class TelegramBotStarterConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        return new TelegramBotsApi(DefaultBotSession.class);
    }

    @Bean
    public UserService userService(UserRepository userRepository, PackageService packageService){
        return new UserServiceImpl(userRepository, packageService);
    }

    @Bean
    public PackageService packageService(PackageRepository packageRepository){
        return new PackageServiceImpl(packageRepository);
    }

    @Bean
    public TelegramBotInitializer telegramBotInitializer(TelegramBotsApi telegramBotsApi,
                                                         UserServiceImpl userService,
                                                         PackageServiceImpl packageServiceImpl) throws TelegramApiException {
        return new TelegramBotInitializer(telegramBotsApi, userService, packageServiceImpl);
    }
}
