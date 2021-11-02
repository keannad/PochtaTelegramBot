package ru.bletenkov.pochtabot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jvnet.hk2.annotations.Service;
import ru.bletenkov.pochtabot.model.MailPackage;
import ru.bletenkov.pochtabot.model.User;
import ru.bletenkov.pochtabot.repository.UserRepository;
import ru.bletenkov.pochtabot.service.PackageService;
import ru.bletenkov.pochtabot.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PackageService packageService;

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUserByChatId(Long chatId){
        return userRepository.getByChatId(chatId);
    }

    @Override
    public boolean isRegistered(Long chatId){
        return userRepository.existsById(chatId);
    }

    @Override
    public void saveUserModel(User user){
        userRepository.save(user);
    }

    @Override
    public boolean deleteUserModel(Long chatId){
        User user = userRepository.getByChatId(chatId);
        List<MailPackage> packageList = packageService.get(chatId);
        for (MailPackage pack : packageList) {
            packageService.delete(pack);
        }
        userRepository.delete(user);
        return true;
    }

}
