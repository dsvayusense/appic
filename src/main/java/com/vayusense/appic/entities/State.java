package com.vayusense.appic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Document(collection = "state")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class State {

    @Id
    private String id;
    private String batchId;
    private Integer fermenterVolInL;
    private LocalDateTime batchStartDate;
    private String fermenterName;
    private int BatchAgeInMin;
    private int batchSerialNumber;
    private Monitored monitored;
    private Controller controller;




}
