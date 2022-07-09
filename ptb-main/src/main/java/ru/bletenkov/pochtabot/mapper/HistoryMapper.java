package ru.bletenkov.pochtabot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.bletenkov.pochtabot.db.tables.pojos.History;
import ru.bletenkov.pochtabot.dto.history.HistoryDto;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class HistoryMapper {

    public abstract HistoryDto map(History value);

    public abstract List<HistoryDto> map(List<History> value);

    public abstract History mapToEntity(HistoryDto value);

}
