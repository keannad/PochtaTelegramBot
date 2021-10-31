package ru.bletenkov.pochtabot.service;

import ru.bletenkov.pochtabot.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserByChatId(Long chatId);

    boolean isRegistered(Long chatId);

    void saveUserModel(User user);

    boolean deleteUserModel(Long chatId);
}
