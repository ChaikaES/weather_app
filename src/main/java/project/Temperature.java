package project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature {
    @JsonProperty(value = "Value")
    private Float value;

    @JsonProperty(value = "Unit")
    private String unit;

    public void setValue(Float value) {
        this.value = value;
    }

    public Float getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
