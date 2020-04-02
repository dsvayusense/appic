package com.vayusense.appic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vayusense.appic.errorhandler.Adult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class StateDto {

    @ApiModelProperty(value = "batchId", example = "020Prod")
    @JsonProperty("batchId")
    @NotBlank (message = "batchID is a required field")
    @Size(min = 5, max = 8, message = "batchId field cannot be longer than 8 characters")
    private String batchId;
    @JsonProperty("fermenterVolInL")
    @NotNull(message = "fermenterVolInL is a required field")
    @PositiveOrZero(message = "fermenterVolInL cannot be less than {0} ")
    private Integer fermenterVolInL;
    @JsonProperty("batchStartDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message = "batchStartDate is a required field")
    @PastOrPresent(message = "batchStartDate should be in the past or today")
    private LocalDateTime batchStartDate;
    @ApiModelProperty(value = "fermenterName", example = "Prod/RnDA/RnDB")
    @JsonProperty("fermenterName")
    @NotBlank(message = "fermenterName is a required field")
    @Adult(message = "The parameters can be {Prod,RnDA,RnDB}")
    private String fermenterName;
    @JsonProperty("batchAgeInMin")
    @NotNull(message = "batchAgeInMin is a required field")
    @PositiveOrZero(message = "batchAgeInMin cannot be less than {0} ")
    private Integer batchAgeInMin;
    @JsonProperty("batchSerialNumber")
    @NotNull(message = "batchSerialNumber is a required field")
    @PositiveOrZero(message = "batchSerialNumber cannot be less than {0} ")
    private Integer batchSerialNumber;
    @JsonProperty("monitored")
    @NotNull(message = "monitore is a required field")
    private MonitoredDto monitored;
    private ControllerDto controller;

}
