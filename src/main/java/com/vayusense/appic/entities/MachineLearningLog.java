package com.vayusense.appic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Document(collection = "machinelearninglog")
@Data
@NoArgsConstructor
public class MachineLearningLog {

    @Id
    private String id;
    private String batchId;
    private Integer FermenterVolInL;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime BatchStartDate;
    private String FermenterName;
    private Integer BatchAgeInMin;
    private Integer batchSerialNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime calcDate;
    private String message;
    private String module;
    private String level;

}
