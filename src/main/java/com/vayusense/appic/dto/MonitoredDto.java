package com.vayusense.appic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
public class MonitoredDto {


    private Double temperature;
    private Double pressure;
    private Double airFlow;
    private Double fs;
    private Double fa;
    private Double agitation;
    private Double onlinePH;
    private int offlinePHTimeInMin;
    private Double offlinePH;
    @JsonProperty("do")
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

    public void setAll(Double temperature, Double pressure, Double airFlow, Double fs, Double fa, Double agitation, Double onlinePH, int offlinePHTimeInMin, Double offlinePH,
                       Double dissolvedOxygen, Double co2, Double mass, Double power, Double incyte, Double amnCocent, int amnTimeInMin, Double tobraConcent,
                       int tobraTimeInMin, Double kanamConcent, int kanamTimeInMin, Double pcv, int pcvTimeInMin){
        this.temperature = temperature;
        this.pressure = pressure;
        this.airFlow = airFlow;
        this.fs = fs;
        this.fa = fa;
        this.agitation = agitation;
        this.onlinePH = onlinePH;
        this.offlinePHTimeInMin = offlinePHTimeInMin;
        this.offlinePH = offlinePH;
        this.dissolvedOxygen = dissolvedOxygen;
        this.co2 = co2;
        this.mass = mass;
        this.power = power;
        this.incyte = incyte;
        this.amnCocent = amnCocent;
        this.amnTimeInMin = amnTimeInMin;
        this.tobraConcent = tobraConcent;
        this.tobraTimeInMin = tobraTimeInMin;
        this.kanamConcent = kanamConcent;
        this.kanamTimeInMin = kanamTimeInMin;
        this.pcv = pcv;
        this.pcvTimeInMin = pcvTimeInMin;

    }

}
