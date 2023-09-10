package com.assessment.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Current (
        @JsonProperty("observation_time")
        String observationTime,
        Integer temperature,
        @JsonProperty("weather_icons")
        List<String> weatherIcons,
        @JsonProperty("weather_code")
        Integer weatherCode,
        @JsonProperty("weather_descriptions")
        List<String> weatherDescriptions,
        @JsonProperty("wind_speed")
        Integer windSpeed,
        @JsonProperty("wind_degree")
        Integer windDegree,
        @JsonProperty("wind_dir")
        String windDirection,
        Integer pressure,
        Integer precip,
        Integer humidity,
        @JsonProperty("cloudclover")
        String cloudCover,
        @JsonProperty("feelslike")
        String feelslike,
        @JsonProperty("uv_index")
        String uvIndex,
        String visibility,
        @JsonProperty("is_day")
        String isDay
        ) {
}
