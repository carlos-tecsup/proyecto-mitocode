package com.tecsup.microservices;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jakarta.ws.rs.HttpMethod;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;


@OpenAPIDefinition(
        servers = {
                @Server(url = "http://localhost:9080/api", description = "Gateway Server URL")
        }
)
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("API Gateway Microservice")
                .description("API Cloud Gateway")
                .version("1.0.0"));
    }


    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/query-license/v3/api-docs").and().method(HttpMethod.GET).uri("lb://license-query-service"))
                .route(r -> r.path("/command-license/v3/api-docs").and().method(HttpMethod.GET).uri("lb://license-command-service"))
                .route(r -> r.path("/auth-license/v3/api-docs").and().method(HttpMethod.GET).uri("lb://authentication-servet-jwt"))
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.addAllowedOrigin("*"); // Origen permitido (Swagger UI)
        configuration.addAllowedMethod("*");                    // Permitir todos los métodos HTTP
        configuration.addAllowedHeader("*");                    // Permitir todos los encabezados
        configuration.setAllowCredentials(true);                // Permitir cookies o credenciales

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplicar a todas las rutas
        return source;
    }



}