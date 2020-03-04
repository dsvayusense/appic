package com.vayusense.appic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class VayumeterLogDto {

    private LocalDateTime updateDate;
    private String level;
    private String customer;
    private String message;
}
