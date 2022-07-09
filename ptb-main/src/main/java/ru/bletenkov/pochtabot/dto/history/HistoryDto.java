package ru.bletenkov.pochtabot.dto.history;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
public class HistoryDto{

    private Long id;
    private Long mailPackageId;
    private Integer typeId;
    private String typeName;
    private Integer attributeId;
    private String attributeName;
    private LocalDateTime operDate;

}
