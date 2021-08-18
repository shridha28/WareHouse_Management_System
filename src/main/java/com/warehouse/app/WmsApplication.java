package com.warehouse.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class})
@ComponentScan (basePackages = "com.warehouse")
@EnableMongoRepositories("com.warehouse.repo")
@EntityScan("com.warehouse.dao.*")
public class WmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WmsApplication.class, args);
	}

}
