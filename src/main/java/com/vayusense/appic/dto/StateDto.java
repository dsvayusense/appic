package com.vayusense.appic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class StateDto {
    @JsonProperty("co2")
    private Integer co2 = null;

    @JsonProperty("ph")
    private Integer ph = null;

    public StateDto co2(Integer co2) {
        this.co2 = co2;
        return this;
    }

    /**
     * Get co2
     * @return co2
     **/
    @ApiModelProperty(value = "")


    public Integer getCo2() {
        return co2;
    }

    public void setCo2(Integer co2) {
        this.co2 = co2;
    }

    public StateDto ph(Integer ph) {
        this.ph = ph;
        return this;
    }

    /**
     * Get ph
     * @return ph
     **/
    @ApiModelProperty(value = "")


    public Integer getPh() {
        return ph;
    }

    public void setPh(Integer ph) {
        this.ph = ph;
    }


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
