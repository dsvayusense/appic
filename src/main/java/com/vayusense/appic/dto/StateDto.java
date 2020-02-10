package com.vayusense.appic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Data
@NoArgsConstructor
public class StateDto {

    @JsonProperty("co2")
    private Integer co2 = null;
    @JsonProperty("ph")
    private Integer ph = null;
    @JsonProperty("details")
    private Object details;

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StateDto stateDto = (StateDto) o;
        return Objects.equals(this.co2, stateDto.co2) &&
                Objects.equals(this.ph, stateDto.ph);
    }

    @Override
    public int hashCode() {
        return Objects.hash(co2, ph);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class State {\n");
        sb.append("    co2: ").append(toIndentedString(co2)).append("\n");
        sb.append("    ph: ").append(toIndentedString(ph)).append("\n");
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
