package ru.bletenkov.pochtabot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.bletenkov.pochtabot.db.tables.pojos.MailPackage;
import ru.bletenkov.pochtabot.dto.mailpackage.MailPackageDto;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MailPackageMapper {

    public abstract MailPackageDto map(MailPackage value);

    public abstract List<MailPackageDto> map(List<MailPackage> value);

    public abstract MailPackage mapToEntity(MailPackageDto value);

}
