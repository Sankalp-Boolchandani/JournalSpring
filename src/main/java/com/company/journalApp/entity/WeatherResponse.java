package com.company.journalApp.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {

    public Current current;

    @Setter
    @Getter
    public class Current{
        public String observation_time;
        public int temperature;
        public int wind_speed;
        public int humidity;
        public int feelslike;
    }
}