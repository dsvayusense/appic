package com.vayusense.appic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "ControllerCalculation")
@Data
@NoArgsConstructor
public class ControllerCalculation {
    @Id
    private String id;
    private Integer fermenterSize;
    private int serial;
    private int year;
    private String fermenterName;
    private String batchId;
    private LocalDateTime updateDate;
    private int batchTimeInMin;
    private float fsAction;
    private float faAction;
    private float agitationAction;
    private float dexstroseEstimated;
    private float ammoniaEstimated;
    private float ammoniaWanted;
    private Boolean isAmmoniaUpdate;

}
