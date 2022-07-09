package ru.bletenkov.pochtabot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jvnet.hk2.annotations.Service;
import ru.bletenkov.pochtabot.dto.history.HistoryDto;
import ru.bletenkov.pochtabot.dto.mailpackage.MailPackageDto;
import ru.bletenkov.pochtabot.mapper.HistoryMapper;
import ru.bletenkov.pochtabot.repository.HistoryRepository;
import ru.bletenkov.pochtabot.service.HistoryService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    private final HistoryMapper historyMapper;

    @Override
    public List<HistoryDto> getHistoryRecordsList(Long packageId) {
        return historyMapper.map(historyRepository.fetchByMailPackageId(packageId));
    }

    @Override
    public List<HistoryDto> getHistoryRecordsList(MailPackageDto mailPackage) {
        return getHistoryRecordsList(mailPackage.getId());
    }

    @Override
    public HistoryDto getLastRecord(Long packageId) {
        return historyMapper.map(historyRepository.getLastRecord(packageId));
    }

    @Override
    public HistoryDto getLastRecord(MailPackageDto mailPackage) {
        return getLastRecord(mailPackage.getId());
    }
}
