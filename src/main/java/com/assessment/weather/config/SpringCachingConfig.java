package com.assessment.weather.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.assessment.weather.constants.Constants;

@Configuration
@EnableCaching
public class SpringCachingConfig {
    @Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager(Constants.WEATHER_CACHE_NAME);
    }
}
