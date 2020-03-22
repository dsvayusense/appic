package com.vayusense.appic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ControllerDto {

    private LocalDateTime calcDate;
    private float fsAction;
    private float faAction;
    private int batchTimeInMin;
    private float agitationAction;
    private float dcEstim;
    private float amnEstim;
    private float amnWanted;
    private Boolean isAmnUpdate;
}
