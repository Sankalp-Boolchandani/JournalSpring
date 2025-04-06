package com.company.journalApp.service;

import com.company.journalApp.entity.AppConfigData;
import com.company.journalApp.repository.AppConfigDataRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppConfigDataService {

    @Autowired
    private AppConfigDataRepository repository;

    private Map<String, String> appConfig;

    public Map<String, String> getAppConfig() {
        return appConfig;
    }

    @PostConstruct
    public void init(){
        appConfig=new HashMap<>();
        List<AppConfigData> configs = repository.findAll();
        for (AppConfigData config: configs){
            appConfig.put(config.getKey(), config.getValue());
        }
    }
}
