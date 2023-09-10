package com.assessment.weather.dto;

import java.time.LocalDateTime;

public record WeatherResponse(Request request,
                              Location location,
                              Current current) {
}
