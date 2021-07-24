package ru.bletenkov.pochtabot.services;
/*
    Created by IntelliJ IDEA
    @author:     Bletenkov Kirill aka Keannad
    @date:       19.07.2021
    @project:    PochtaTelegramBot
*/

import org.springframework.beans.factory.annotation.Autowired;
import ru.bletenkov.pochtabot.models.PackageModel;
import ru.bletenkov.pochtabot.repos.PackageRepository;

public class PackageService {

    private PackageRepository packageRepository;

    @Autowired
    public void setPackageRepository(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public void savePackage(PackageModel pack){
        packageRepository.save(pack);
    }

    public PackageModel getByCodeAndUserId(String code, Long userId){
        return packageRepository.getByCodeAndUserId(code, userId);
    }

    public void deleteByCode(String code, Long userId){
        packageRepository.delete(packageRepository.getByCodeAndUserId(code, userId));
    }
}
