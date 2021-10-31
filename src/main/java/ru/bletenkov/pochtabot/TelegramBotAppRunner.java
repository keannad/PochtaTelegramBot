package ru.bletenkov.pochtabot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;

@Slf4j
public class TelegramBotAppRunner implements org.springframework.boot.ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("========================================================");
        log.info("==        Telegram Russian Post Bot is started        ==");
        log.info("========================================================");
    }
}
