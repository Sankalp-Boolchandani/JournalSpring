package com.company.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

//---------------------------------------------------------------------------------------------------------------------------
// Writing this class because when I tried to autowired this in WeatherService, the application was not starting and giving
// the error consider defining a "bean of class RestTemplate". After adding this class, this error was resolved.
//---------------------------------------------------------------------------------------------------------------------------