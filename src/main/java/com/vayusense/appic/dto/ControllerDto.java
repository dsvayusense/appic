package com.vayusense.appic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ControllerDto {

    private LocalDateTime calcDate;
    private Double fsAction;
    private Double faAction;
    private int batchTimeInMin;
    private Double agitationAction;
    private Double dcEstim;
    private Double amnEstim;
    private Double amnWanted;
    private Boolean isAmnUpdate;


}
