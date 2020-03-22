package com.vayusense.appic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class MachineLearningLogDto {

    private String batchId;
    private Integer fermenterVolInL;
    private LocalDateTime batchStartDate;
    private String fermenterName;
    private int batchAgeInMin;
    private String batchSerialNumber;
    private LocalDateTime calcDate;
    private String message;
    private String module;
    private String level;
}
