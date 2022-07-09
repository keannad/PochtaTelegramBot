package ru.bletenkov.pochtabot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
public class TelegramBotAppRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        log.info("========================================================");
        log.info("==        Telegram Russian Post Bot is started        ==");
        log.info("========================================================");
    }
}
