package com.assessment.weather.dto;

import com.assessment.weather.model.WeatherEntity;

import java.time.LocalDateTime;

public record WeatherDto (String cityName,
                          String country,
                          Integer temperature,
                          LocalDateTime updatedTime)
{
    public static WeatherDto convert(WeatherEntity from) {
        return new WeatherDto(from.getCityName(), from.getCountry(), from.getTemperature(), from.getUpdatedTime());
    }
}
