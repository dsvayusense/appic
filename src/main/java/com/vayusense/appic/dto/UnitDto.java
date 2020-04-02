package com.vayusense.appic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UnitDto {

    @JsonProperty("batchId")
    private String id;
    private String fermenterVolInL;
    private LocalDateTime batchStartDate;
    private String temperature;
    private String pressure;
    private String airFlow;
    private String fs;
    private String fa;
    private String agitation;
    @JsonProperty("do")
    private String dissolvedOxygen;
    private String co2;
    private String mass;
    private String power;
    private String incyte;
    private String amnConcent;
    private String tobraConcent;
    private String kanamConcent;
    private String dextroseConcent;
    private String pcv;
}
