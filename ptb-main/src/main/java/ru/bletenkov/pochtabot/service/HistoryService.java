package ru.bletenkov.pochtabot.service;

import ru.bletenkov.pochtabot.dto.history.HistoryDto;
import ru.bletenkov.pochtabot.dto.mailpackage.MailPackageDto;

import java.util.List;

public interface HistoryService {

    List<HistoryDto> getHistoryRecordsList(Long packageId);

    List<HistoryDto> getHistoryRecordsList(MailPackageDto mailPackage);

    HistoryDto getLastRecord(Long packageId);

    HistoryDto getLastRecord(MailPackageDto mailPackage);

}
