package ru.bletenkov.pochtabot.repos;
/*
    Created by IntelliJ IDEA
    @author:     Bletenkov Kirill aka Keannad
    @date:       19.07.2021
    @project:    PochtaTelegramBot
*/

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bletenkov.pochtabot.models.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {
    UserModel getByChatId(Long chatId);
}
