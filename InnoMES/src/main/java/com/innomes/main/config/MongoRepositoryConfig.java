package com.innomes.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "e3view.factoryview.main.mongorepository")
public class MongoRepositoryConfig {

}
