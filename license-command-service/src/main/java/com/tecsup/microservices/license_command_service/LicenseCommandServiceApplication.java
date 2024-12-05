package com.tecsup.microservices.license_command_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tecsup.microservices.*")
public class LicenseCommandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicenseCommandServiceApplication.class, args);
	}

}
