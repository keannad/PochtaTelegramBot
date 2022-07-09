package ru.bletenkov.pochtabot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jvnet.hk2.annotations.Service;
import ru.bletenkov.pochtabot.db.tables.pojos.MailPackage;
import ru.bletenkov.pochtabot.dto.mailpackage.MailPackageDto;
import ru.bletenkov.pochtabot.mapper.MailPackageMapper;
import ru.bletenkov.pochtabot.repository.MailPackageRepository;
import ru.bletenkov.pochtabot.service.MailPackageService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailPackageServiceImpl implements MailPackageService {

    private final MailPackageRepository mailPackageRepository;

    private final MailPackageMapper mailPackageMapper;

    @Override
    public void delete(MailPackageDto pack) {
        mailPackageRepository.deleteById(pack.getId());
    }

    @Override
    public MailPackageDto save(MailPackageDto pack) {

        MailPackage mailPackage = mailPackageMapper.mapToEntity(pack);
        mailPackageRepository.insert(mailPackage);

        return mailPackageMapper.map(mailPackage);
    }

    @Override
    public List<MailPackageDto> getByUserId(Long userId) {
        return mailPackageMapper.map(mailPackageRepository.fetchByUserId(userId));
    }

    @Override
    public MailPackageDto getByCodeAndUserId(Long userId, String code) {
        return mailPackageMapper.map(mailPackageRepository.findByUserIdAndCode(userId, code));
    }

    @Override
    public void deleteByUserIdAndCode(Long userId, String code) {
        MailPackage mailPackage = mailPackageRepository.findByUserIdAndCode(userId, code);
        if (mailPackage != null) {
            mailPackageRepository.delete();
        }
    }
}
