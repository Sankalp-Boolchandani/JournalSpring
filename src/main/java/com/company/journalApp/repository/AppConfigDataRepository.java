package com.company.journalApp.repository;

import com.company.journalApp.entity.AppConfigData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppConfigDataRepository extends MongoRepository<AppConfigData, String> {
}
