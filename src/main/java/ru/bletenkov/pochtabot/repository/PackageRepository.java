package ru.bletenkov.pochtabot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bletenkov.pochtabot.model.MailPackage;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<MailPackage, Long> {

    List<MailPackage> getAllByUserId(Long userId);

    MailPackage getByUserIdAndCode(Long userId, String code);

    void deleteByUserIdAndCode(Long userId, String code);

}
