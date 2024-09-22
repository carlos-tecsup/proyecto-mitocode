package com.tecsup.microservices.licence_query_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tecsup.microservices.*")
public class LicenceQueryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicenceQueryServiceApplication.class, args);
	}

}
