package project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherResponse {
    @JsonProperty(value = "WeatherText")
    private String weatherText;

    private Float temperature;
    private String unit;

    @JsonProperty("Temperature")
    private void unpackTemperatureFromNestedObject(Map<String, Temperature> temperatureObjects) {
        Temperature temperatureObject = temperatureObjects.get("Metric");
        temperature = temperatureObject.getValue();
        unit = temperatureObject.getUnit();
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public String toString() {
        return weatherText + ", " + Float.toString(temperature) + unit;
    }
}
