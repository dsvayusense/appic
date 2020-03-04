package com.vayusense.appic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "vayuemeterlog")
@Data
@NoArgsConstructor
public class VayumeteLog {
    private String id;
    private LocalDateTime updateDate;
    private String level;
    private String customer;
    private String message;
}
