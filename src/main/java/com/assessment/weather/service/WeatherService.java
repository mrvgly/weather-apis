package com.assessment.weather.service;

import com.assessment.weather.dto.WeatherDto;
import com.assessment.weather.dto.WeatherResponse;
import com.assessment.weather.model.WeatherEntity;
import com.assessment.weather.repository.WeatherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class WeatherService {
    private static final String API_URL = "http://api.weatherstack.com/current?access_key=9d1e6dc2506b79fb808859adc8af9245&query=";
    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public WeatherService(WeatherRepository weatherRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public WeatherDto getWeatherByCityName(String city) {
        Optional<WeatherEntity> weatherEntityOptional = weatherRepository.findFirstByRequestedCityNameOrderByUpdatedTimeDesc(city);
        if (!weatherEntityOptional.isPresent()) {
            return WeatherDto.convert(getWeatherFromWeatherStack(city));
        }

        return WeatherDto.convert(weatherEntityOptional.get());
    }

    private WeatherEntity getWeatherFromWeatherStack(String city){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL + city, String.class);

        try {
            WeatherResponse weatherResponse = objectMapper.readValue(responseEntity.getBody(), WeatherResponse.class);
            return saveWeatherEntity(city, weatherResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private WeatherEntity saveWeatherEntity(String city, WeatherResponse weatherResponse){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        WeatherEntity weatherEntity = new WeatherEntity(city,
                weatherResponse.location().name(),
                weatherResponse.location().country(),
                weatherResponse.current().temperature(),
                LocalDateTime.now(),
                LocalDateTime.parse(weatherResponse.location().localtime(), dateTimeFormatter));

        return weatherRepository.save(weatherEntity);
    }
}