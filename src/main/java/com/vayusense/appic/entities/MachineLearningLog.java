package com.vayusense.appic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "machinelearninglog")
@Data
@NoArgsConstructor
public class MachineLearningLog {

    @Id
    private String id;
    private Integer fermenterSize;
    private int serial;
    private int year;
    private String fermenterName;
    private String batchId;
    private LocalDateTime updateDate;
    private int batchTimeInMin;
    private String message;
    private String module;
    private String level;

}
