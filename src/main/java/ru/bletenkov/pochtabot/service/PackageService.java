package ru.bletenkov.pochtabot.service;

import ru.bletenkov.pochtabot.model.MailPackage;

import java.util.List;

public interface PackageService {


    void delete(MailPackage pack);

    void save(MailPackage pack);

    List<MailPackage> get(Long userId);

    MailPackage getByCodeAndUserId(Long userId, String code);

    void deleteByUserIdAndCode(Long userId, String code);

}
