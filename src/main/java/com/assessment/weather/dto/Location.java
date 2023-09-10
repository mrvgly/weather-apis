package com.assessment.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Location(String name,
                       String country,
                       String localtime,
                       String region,
                       Double lat,
                       Double lon,
                       @JsonProperty("timezone_id")
                       String timezoneId,
                       @JsonProperty("localtime_epoch")
                       String localtimeEpoch,
                       @JsonProperty("utc_offset")
                       String utcOffset
                       ) {
}
