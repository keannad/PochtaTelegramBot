package ru.bletenkov.pochtabot.service;

import ru.bletenkov.pochtabot.model.HistoryRecord;
import ru.bletenkov.pochtabot.model.MailPackage;

import java.util.List;

public interface HistoryRecordService {

    List<HistoryRecord> getHistoryRecordsList(Long packageId);

    List<HistoryRecord> getHistoryRecordsList(MailPackage mailPackage);

    HistoryRecord getLastRecord(Long packageId);

    HistoryRecord getLastRecord(MailPackage mailPackage);

    void save(HistoryRecord record);

    void delete(Long historyRecordId);

    void delete(HistoryRecord record);

}
