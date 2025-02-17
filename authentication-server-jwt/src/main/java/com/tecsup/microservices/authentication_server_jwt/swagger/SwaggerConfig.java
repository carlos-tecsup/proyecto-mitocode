package com.tecsup.microservices.authentication_server_jwt.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Auth Microservice", version = "1.0", description = "API documentation for Authentication Microservice"),
        servers = {
                @Server(url = "http://localhost:9080/api/auth-license", description = "Cloud Gateway Server")
        }
)
public class SwaggerConfig {

}
