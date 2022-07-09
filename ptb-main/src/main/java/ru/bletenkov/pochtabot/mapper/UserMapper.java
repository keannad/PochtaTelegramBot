package ru.bletenkov.pochtabot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.bletenkov.pochtabot.db.tables.pojos.User;
import ru.bletenkov.pochtabot.dto.user.UserDto;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "language", source = "lang")
    public abstract UserDto map(User value);

    public abstract List<UserDto> map(List<User> value);

    public abstract User mapToEntity(UserDto value);

}
