package com.vayusense.appic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
public class DeviceEvent {

    private String message;
    private Date date;


}
