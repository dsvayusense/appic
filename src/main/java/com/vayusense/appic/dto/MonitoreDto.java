package com.vayusense.appic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonitoreDto {

    private float temperature;
    private float pressure;
    private float airFlow;
    private float fs;
    private float fa;
    private float agitation;
    private float onlinePH;
    private int offlinePHTimeInMin;
    private float offlinePH;
    @JsonProperty("do")
    private float dissolvedOxygen;
    private float co2;
    private float mass;
    private float power;
    private float incyte;
    private float amnCocent;
    private int amnTimeInMin;
    private float tobraConcent;
    private int tobraTimeInMin;
    private float kanamConcent;
    private int kanamTimeInMin;
    private float pcv;
    private int pcvTimeInMin;
}
