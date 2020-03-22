package com.vayusense.appic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Document(collection = "state")
@Data
@AllArgsConstructor
public class State {

    @Id
    private String id;
    @Indexed(name = "batch_id",unique = true)
    private String batchId;
    private Integer fermenterVolInL;
    private LocalDateTime batchStartDate;
    private String fermenterName;
    private int BatchAgeInMin;
    private String batchSerialNumber;
    private Monitore monitore;
    private Controller controller;




}
