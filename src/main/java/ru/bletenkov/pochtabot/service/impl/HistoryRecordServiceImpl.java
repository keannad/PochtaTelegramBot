package ru.bletenkov.pochtabot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jvnet.hk2.annotations.Service;
import ru.bletenkov.pochtabot.model.HistoryRecord;
import ru.bletenkov.pochtabot.model.MailPackage;
import ru.bletenkov.pochtabot.repository.HistoryRecordRepository;
import ru.bletenkov.pochtabot.service.HistoryRecordService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HistoryRecordServiceImpl implements HistoryRecordService {

    private final HistoryRecordRepository historyRecordRepository;

    @Override
    public List<HistoryRecord> getHistoryRecordsList(Long packageId) {
        return historyRecordRepository.findAllByPackageId(packageId);
    }

    @Override
    public List<HistoryRecord> getHistoryRecordsList(MailPackage mailPackage) {
        Long packageId = mailPackage.getId();
        return getHistoryRecordsList(packageId);
    }

    @Override
    public HistoryRecord getLastRecord(Long packageId) {
        return historyRecordRepository.getLastRecord(packageId);
    }

    @Override
    public HistoryRecord getLastRecord(MailPackage mailPackage) {
        Long packageId = mailPackage.getId();
        return getLastRecord(packageId);
    }

    @Override
    public void save(HistoryRecord record) {
        historyRecordRepository.save(record);
    }

    @Override
    public void delete(Long historyRecordId) {
        HistoryRecord record = historyRecordRepository.getById(historyRecordId);
        delete(record);
    }

    @Override
    public void delete(HistoryRecord record) {
        historyRecordRepository.delete(record);
    }
}
