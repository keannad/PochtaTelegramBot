package ru.bletenkov.pochtabot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bletenkov.pochtabot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByChatId(Long chatId);

}
