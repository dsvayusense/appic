package com.vayusense.appic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Controller {

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
