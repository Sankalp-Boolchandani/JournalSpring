package com.company.journalApp.service;

import com.company.journalApp.entity.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String API_KEY="685f29d2d229599fff28189fa75b2c1d";
    private static final String baseUrl="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    public String getWeather(String city, String name){
        String replacedUrl = baseUrl.replace("API_KEY", API_KEY).replace("CITY", city);

        ResponseEntity<WeatherResponse> weather = restTemplate.exchange(replacedUrl, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse weatherResponse=weather.getBody();

        return "Hi "+name+"! The tempreture for the day is "+weatherResponse.getCurrent().getTemperature();
    }

}
