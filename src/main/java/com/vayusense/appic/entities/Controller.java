package com.vayusense.appic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Controller {

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
