package com.vayusense.appic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vayusense.appic.errorhandler.Adult;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StateDto {

    /*@JsonProperty("co2")
    @NotNull(message = "co2 is a required field")
    @PositiveOrZero(message = "co2 cannot be less than {0}")
    private Integer co2 = null;
    @JsonProperty("ph")
    @NotNull(message = "ph is a required field")
    @PositiveOrZero(message = "ph cannot be less than {0} ")
    private Integer ph = null;
    @JsonProperty("startime")
    @NotNull(message = "startime is a required field")
    @PastOrPresent(message = "startime should be in the past or today")
    //@Adult(message = "{state.startime.adult}")
    private LocalDate startime;
    @JsonProperty("endtime")
    @NotNull(message = "endtime is a required field")
    @PastOrPresent(message = "endtime should be in the past or today")
    //@Adult(message = "{state.endtime.adult}")
    private LocalDate endtime;
    @JsonProperty("details")
    private Object details;*/
    private String batchId;
    private Integer fermenterVolInL;
    private LocalDateTime batchStartDate;
    private String fermenterName;
    private int batchAgeInMin;
    private String batchSerialNumber;
    private MonitoreDto monitore;
    private ControllerDto controller;

}
