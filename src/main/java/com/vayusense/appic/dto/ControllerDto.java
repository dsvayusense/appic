package com.vayusense.appic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ControllerDto {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime calcDate;
    private Double fsAction;
    private Double faAction;
    private int batchTimeInMin;
    private Double agitationAction;
    private Double dcEstim;
    private Double amnEstim;
    private Double amnWanted;
    private Boolean isAmnUpdate;


}
