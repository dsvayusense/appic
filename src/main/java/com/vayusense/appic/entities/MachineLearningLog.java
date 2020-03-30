package com.vayusense.appic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "machinelearninglog")
@Data
@NoArgsConstructor
public class MachineLearningLog {

    @Id
    private String id;
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
