package com.example.mongodbdemo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MongodbConfig {

    @Bean
    public MongoClient mongoClient(@Value("${mongodb.connection.uri:}") String uri) {
        log.info("connect to mongodb: {}", uri);
        return MongoClients.create(uri);
    }


}
