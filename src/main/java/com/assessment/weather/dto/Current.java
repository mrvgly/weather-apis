package com.assessment.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Current (
        Integer temperature) {
}
