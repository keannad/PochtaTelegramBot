package ru.bletenkov.pochtabot.dto.user;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private Long chatId;
    private String language;
    private Boolean isAdmin;

}
