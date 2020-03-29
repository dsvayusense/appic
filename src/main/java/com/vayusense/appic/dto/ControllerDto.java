package com.vayusense.appic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
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

    public void setAll(LocalDateTime calcDate,float fsAction, float faAction, int batchTimeInMin, float agitationAction, float dcEstim, float amnEstim, float amnWanted, Boolean isAmnUpdate){
        this.calcDate = calcDate;
        this.fsAction = fsAction;
        this.faAction = faAction;
        this.batchTimeInMin = batchTimeInMin;
        this.agitationAction = agitationAction;
        this.dcEstim = dcEstim;
        this.amnEstim = amnEstim;
        this.amnWanted = amnWanted;
        this.isAmnUpdate = isAmnUpdate;

    }
}
