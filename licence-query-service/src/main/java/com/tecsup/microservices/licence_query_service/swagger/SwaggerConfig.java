package com.tecsup.microservices.licence_query_service.swagger;

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
        info = @Info(title = "Query Microservice", version = "1.0", description = "API documentation for Query Microservice"),
        servers = {
                @Server(url = "http://localhost:9080/api/query-license", description = "Cloud Gateway Server")
        }
)
public class SwaggerConfig {

        @Bean
        public SecurityScheme securityScheme() {
                return new SecurityScheme()
                        .name("Bearer")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT") .in(SecurityScheme.In.HEADER) // Esto es importante para que el token se envíe en el encabezado
                        .description("Ingrese su token JWT con el prefijo 'Bearer '");
        }

        @Bean
        public SecurityRequirement securityRequirement() {
                return new SecurityRequirement().addList("Bearer");
        }
        @Bean
        public OpenAPI openAPI() {
                return new OpenAPI().addSecurityItem(new SecurityRequirement().
                                addList("Bearer Authentication"))
                        .components(new Components().addSecuritySchemes
                                ("Bearer Authentication", createAPIKeyScheme()));
        }

        private SecurityScheme createAPIKeyScheme() {
                return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                        .bearerFormat("JWT")
                        .scheme("bearer");
        }
}
