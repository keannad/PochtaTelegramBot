package ru.bletenkov.pochtabot.service;

import ru.bletenkov.pochtabot.dto.user.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserByChatId(Long chatId);

    boolean isRegistered(Long chatId);

    UserDto save(UserDto user);

    void delete(Long chatId);
}
