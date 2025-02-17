package com.tecsup.microservices.common_models;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class CommonModelsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonModelsApplication.class, args);
    }

}
