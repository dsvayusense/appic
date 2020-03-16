package com.vayusense.appic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "vayuemeterlog")
@Data
@NoArgsConstructor
public class VayumeterLog {
    @Id
    private String id;
    private LocalDateTime insertDate;
    private String level;
    private String customer;
    private String message;
}
