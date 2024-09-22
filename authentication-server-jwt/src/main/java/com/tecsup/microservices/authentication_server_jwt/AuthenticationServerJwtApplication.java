package com.tecsup.microservices.authentication_server_jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tecsup.microservices.*")
public class AuthenticationServerJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServerJwtApplication.class, args);
	}

}
