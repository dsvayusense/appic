package com.vayusense.appic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class ErrorModel   {
    @JsonProperty("timestamp")
    private LocalDate timestamp = null;

    @JsonProperty("message")
    private String message = null;

    @JsonProperty("details")
    private String details = null;

    public ErrorModel timestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Get timestamp
     * @return timestamp
     **/
    @ApiModelProperty(value = "")

    @Valid

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public ErrorModel message(String message) {
        this.message = message;
        return this;
    }

    @ApiModelProperty(required = true, value = "")
    @NotNull

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorModel details(String details) {
        this.details = details;
        return this;
    }

    /**
     * Get details
     * @return details
     **/
    @ApiModelProperty(value = "")


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorModel errorModel = (ErrorModel) o;
        return Objects.equals(this.timestamp, errorModel.timestamp) &&
                Objects.equals(this.message, errorModel.message) &&
                Objects.equals(this.details, errorModel.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, message, details);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ErrorModel {\n");

        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    details: ").append(toIndentedString(details)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

