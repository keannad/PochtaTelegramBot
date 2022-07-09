package ru.bletenkov.pochtabot.dto.mailpackage;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
public class MailPackageDto {

    private Long id;
    private Long userId;
    private String code;
    private Boolean isDead;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
