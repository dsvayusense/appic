package com.vayusense.appic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateDto {

    //@Adult(message = "{state.endtime.adult}")

    @JsonProperty("batchId")
    @NotBlank (message = "batchID is a required field")
    @Size(min = 5, max = 8, message = "batchId field cannot be longer than 8 characters")
    private String batchId;
    @JsonProperty("fermenterVolInL")
    @NotNull(message = "fermenterVolInL is a required field")
    @PositiveOrZero(message = "fermenterVolInL cannot be less than {0} ")
    private Integer fermenterVolInL;
    @JsonProperty("batchStartDate")
    @NotNull(message = "batchStartDate is a required field")
    @PastOrPresent(message = "batchStartDate should be in the past or today")
    private LocalDateTime batchStartDate;
    @JsonProperty("fermenterName")
    @NotBlank(message = "fermenterName is a required field")
    private String fermenterName;
    @JsonProperty("batchAgeInMin")
    @NotNull(message = "batchAgeInMin is a required field")
    @PositiveOrZero(message = "batchAgeInMin cannot be less than {0} ")
    private int batchAgeInMin;
    @JsonProperty("batchSerialNumber")
    @NotNull(message = "batchSerialNumber is a required field")
    @PositiveOrZero(message = "batchSerialNumber cannot be less than {0} ")
    private int batchSerialNumber;
    @JsonProperty("monitored")
    @NotNull(message = "monitore is a required field")
    private MonitoredDto monitored;
    private ControllerDto controller;

}
