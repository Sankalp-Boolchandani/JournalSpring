package com.company.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TransactionConfig {

    @Bean
    public PlatformTransactionManager transaction(MongoDatabaseFactory databaseFactory){
        return new MongoTransactionManager(databaseFactory);
    }

}

// without this, transactions wont work. PlatformTransactionManager is an interface and MongoTransactionManager is it implementation.
// it takes MongoDatabaseFactory as a param which is nothing but our DB that is created on application startup.....