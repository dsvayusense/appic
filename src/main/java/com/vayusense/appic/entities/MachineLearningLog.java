package com.vayusense.appic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "machinelearninglog")
@Data
@NoArgsConstructor
public class MachineLearningLog {

    @Id
    private String id;
    @Indexed(name = "batch_id",unique = true)
    private String batchId;
    private Integer FermenterVolInL;
    private LocalDateTime BatchStartDate;
    private String FermenterName;
    private int BatchAgeInMin;
    private String batchSerialNumber;
    private LocalDateTime calcDate;
    private String message;
    private String module;
    private String level;

}
