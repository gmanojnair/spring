package com.manoj.spring.mongodb.springmongodb.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.manoj.spring.mongodb.springmongodb.repository")
public class ApplicationConfiguration {

	
}
