package com.vayusense.appic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ActionDto {

    private String batchId;
    private Integer fermenterVolInL;
    private LocalDateTime batchStartDate;
    private String fermenterName;
    private int batchAgeInMin;
    private int batchSerialNumber;
    private ControllerDto controller;
}
