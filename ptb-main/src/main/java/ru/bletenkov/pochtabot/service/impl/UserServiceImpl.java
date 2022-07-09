package ru.bletenkov.pochtabot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jvnet.hk2.annotations.Service;
import ru.bletenkov.pochtabot.db.tables.pojos.User;
import ru.bletenkov.pochtabot.dto.user.UserDto;
import ru.bletenkov.pochtabot.mapper.UserMapper;
import ru.bletenkov.pochtabot.repository.UserRepository;
import ru.bletenkov.pochtabot.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.map(userRepository.findAll());
    }

    @Override
    public UserDto getUserByChatId(Long chatId) {
        try {
            return userMapper.map(userRepository.fetchByChatId(chatId).stream().findFirst().get());
        }catch (NoSuchElementException ignore) {}
        return null;
    }

    @Override
    public boolean isRegistered(Long chatId) {
        return userRepository.fetchByChatId(chatId).stream().findAny().isPresent();
    }

    @Override
    public UserDto save(UserDto value) {
        User user = userMapper.mapToEntity(value);
        userRepository.insert(user);
        return userMapper.map(user);
    }

    @Override
    public void delete(Long chatId) {
        try {
            User user = userRepository.fetchByChatId(chatId).stream().findFirst().get();
            userRepository.delete(user);
        } catch (NoSuchElementException ignore) {}
    }
}
