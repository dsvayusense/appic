package com.vayusense.appic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
public class Monitored {

    private Double temperature;
    private Double pressure;
    private Double airFlow;
    private Double fs;
    private Double fa;
    private Double agitation;
    private Double onlinePH;
    private int offlinePHTimeInMin;
    private Double offlinePH;
    @Field("do")
    private Double dissolvedOxygen;
    private Double co2;
    private Double mass;
    private Double power;
    private Double incyte;
    private Double amnCocent;
    private int amnTimeInMin;
    private Double tobraConcent;
    private int tobraTimeInMin;
    private Double kanamConcent;
    private int kanamTimeInMin;
    private Double pcv;
    private int pcvTimeInMin;
    private Double dextroseConcent;
    private Double dextroseTimeInMin;





}