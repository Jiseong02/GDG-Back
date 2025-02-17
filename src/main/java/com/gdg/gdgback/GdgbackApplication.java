package com.gdg.gdgback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@EnableMongoAuditing
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class GdgbackApplication {
	public static void main(String[] args) {
		SpringApplication.run(GdgbackApplication.class, args);
	}
}
