package ru.bletenkov.pochtabot.repos;
/*
    Created by IntelliJ IDEA
    @author:     Bletenkov Kirill aka Keannad
    @date:       19.07.2021
    @project:    PochtaTelegramBot
*/

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bletenkov.pochtabot.models.PackageModel;

import java.util.List;

@Repository
public interface PackageRepository extends CrudRepository<PackageModel, Long> {
    List<PackageModel> getByUserId(Long userId);
    PackageModel getByCodeAndUserId(String code, Long userId);
}
