package ru.bletenkov.pochtabot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jvnet.hk2.annotations.Service;
import ru.bletenkov.pochtabot.model.MailPackage;
import ru.bletenkov.pochtabot.repository.PackageRepository;
import ru.bletenkov.pochtabot.service.PackageService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PackageServiceImpl implements PackageService {

    private final PackageRepository packageRepository;

    @Override
    public void save(MailPackage model){
        packageRepository.save(model);
    }

    @Override
    public void delete(MailPackage model) {
        packageRepository.delete(model);
    }

    @Override
    public List<MailPackage> get(Long userId) {
        return packageRepository.getAllByUserId(userId);
    }

    @Override
    public MailPackage getByCodeAndUserId(Long userId, String code){
        return packageRepository.getByUserIdAndCode(userId, code);
    }

    @Override
    public void deleteByUserIdAndCode(Long userId, String code){
        packageRepository.deleteByUserIdAndCode(userId, code);
    }

}
