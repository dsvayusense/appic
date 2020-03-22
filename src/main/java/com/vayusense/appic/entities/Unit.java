package com.vayusense.appic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "unit")
@AllArgsConstructor
@Data
public class Unit {

    @Id
    private String id;
    @Indexed(name = "batch_id",unique = true)
    private String batchId;
    private Integer fermenterVolInL;
    private LocalDateTime batchStartDate;
    private String temperature;
    private String pressure;
    private String airFlow;
    private String fs;
    private String fa;
    private String agitation;
    @Field("do")
    private float dissolvedOxygen;
    private String co2;
    private String mass;
    private String power;
    private String incyte;
    private String amnConcent;
    private String tobraConcent;
    private String kanamConcent;
    private String pcv;
}
