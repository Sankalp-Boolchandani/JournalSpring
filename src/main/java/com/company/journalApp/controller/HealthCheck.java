package com.company.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping(path="/health-check")
    public String healthCheck(){
        return "OK";
    }

}
