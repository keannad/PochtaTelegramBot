package ru.bletenkov.pochtabot.services;
/*
    Created by IntelliJ IDEA
    @author:     Bletenkov Kirill aka Keannad
    @date:       19.07.2021
    @project:    PochtaTelegramBot
*/

import org.springframework.beans.factory.annotation.Autowired;
import ru.bletenkov.pochtabot.models.PackageModel;
import ru.bletenkov.pochtabot.models.UserModel;
import ru.bletenkov.pochtabot.repos.PackageRepository;
import ru.bletenkov.pochtabot.repos.UserRepository;

import java.util.List;

public class UserService {

    private UserRepository userRepository;
    private PackageRepository packageRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository,
                                  PackageRepository packageRepository) {
         this.userRepository = userRepository;
         this.packageRepository = packageRepository;
    }

    public List<UserModel> getAllUsers(){
        return (List<UserModel>) userRepository.findAll();
    }

    public UserModel getUserByChatId(Long chatId){
        return userRepository.getByChatId(chatId);
    }

    public boolean isRegistered(Long chatId){
        UserModel model = userRepository.getByChatId(chatId);
        return model != null;
    }

    public void saveUserModel(UserModel user){
        userRepository.save(user);
    }

    public boolean deleteUserModel(Long chatId){
        UserModel user = userRepository.getByChatId(chatId);
        List<PackageModel> packageList = packageRepository.getByUserId(chatId);
        for(PackageModel pack : packageList) packageRepository.delete(pack);
        userRepository.delete(user);
        return true;
    }

}
