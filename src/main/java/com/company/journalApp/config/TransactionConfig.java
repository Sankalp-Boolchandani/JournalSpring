package com.company.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class TransactionConfig {

    @Bean
    public PlatformTransactionManager transaction(MongoDatabaseFactory databaseFactory){
        return new MongoTransactionManager(databaseFactory);
    }

}
