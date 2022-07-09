package ru.bletenkov.pochtabot.service;

import ru.bletenkov.pochtabot.dto.mailpackage.MailPackageDto;

import java.util.List;

public interface MailPackageService {


    void delete(MailPackageDto pack);

    MailPackageDto save(MailPackageDto pack);

    List<MailPackageDto> getByUserId(Long userId);

    MailPackageDto getByCodeAndUserId(Long userId, String code);

    void deleteByUserIdAndCode(Long userId, String code);

}
