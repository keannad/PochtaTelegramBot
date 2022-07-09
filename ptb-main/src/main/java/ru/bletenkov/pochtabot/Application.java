package ru.bletenkov.pochtabot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        BotDbConfig.class,
})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}
