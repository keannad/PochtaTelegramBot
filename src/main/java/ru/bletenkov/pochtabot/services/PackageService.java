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

import java.util.Arrays;

public class PackageService {

    private PackageRepository packageRepository;

    @Autowired
    public void setPackageRepository(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public boolean savePackage(PackageModel pack){
        packageRepository.save(pack);
        return true;
    }

    public PackageModel getByCode(String code){
        return packageRepository.getByCode(code);
    }

    public void deleteByCode(String code){
        packageRepository.delete(packageRepository.getByCode(code));
    }
}
