package com.company.journalApp.service;

import com.company.journalApp.entity.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppConfigDataService dataService;

    @Value("${weather_api_key}")
    private String API_KEY;
//    private static final String baseUrl="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    public String getWeather(String city, String name){
        String baseUrl = dataService.getAppConfig().get("weather_url");
        String replacedUrl = baseUrl.replace("<apiKey>", API_KEY).replace("<city>", city);
        ResponseEntity<WeatherResponse> weather = restTemplate.exchange(replacedUrl, HttpMethod.GET, null, WeatherResponse.class);
//      this is for the GET call. for post call, we pass the HttpMethod.POST in the same exchange method and pass the request entity instead of null.
        WeatherResponse weatherResponse=weather.getBody();

        return "Hi "+name+"! The tempreture for the day is "+weatherResponse.getCurrent().getTemperature();
    }

}
