package com.vayusense.appic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "unit")
@NoArgsConstructor
@Data
public class Unit {

    @Id
    private String id;
    private String fermenterVolInL;
    private LocalDateTime batchStartDate;
    private String temperature;
    private String pressure;
    private String airFlow;
    private String fs;
    private String fa;
    private String agitation;
    @Field("do")
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
