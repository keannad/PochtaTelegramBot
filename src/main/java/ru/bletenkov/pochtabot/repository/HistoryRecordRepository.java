package ru.bletenkov.pochtabot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bletenkov.pochtabot.model.HistoryRecord;

import java.util.List;

@Repository
public interface HistoryRecordRepository extends JpaRepository<HistoryRecord, Long> {

    List<HistoryRecord> findAllByPackageId(Long packageId);

    HistoryRecord getLastRecord(Long packageId);
}
