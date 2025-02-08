package ru.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Weather {

    @JsonProperty("temp")
    private int temp;

    @JsonProperty("temp_min")
    private int tempMin;

    @JsonProperty("temp_max")
    private int tempMax;

}
