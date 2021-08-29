package project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherResponse {
    @JsonProperty(value = "WeatherText")
    private String weatherText;

    @JsonProperty(value = "LocalObservationDateTime")
    private String localDate;

    private Double temperature;
    private String unit;

    @JsonProperty("Temperature")
    private void unpackTemperatureFromNestedObject(Map<String, Temperature> temperatureObjects) {
        Temperature temperatureObject = temperatureObjects.get("Metric");
        temperature = temperatureObject.getValue();
        unit = temperatureObject.getUnit();
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public String toString() {
        return weatherText + ", " + temperature + unit;
    }

}
