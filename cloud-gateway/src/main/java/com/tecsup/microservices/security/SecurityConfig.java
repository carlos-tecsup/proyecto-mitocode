package com.tecsup.microservices.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange(auth ->
                        auth.pathMatchers(
                        "/v3/**",        // Especificación OpenAPI (JSON)
                        "/webjars/swagger-ui/**",         // Recursos de Swagger UI
                        "/swagger-ui.html",       // Página principal de Swagger UI
                        "/query-license/v3/api-docs/**",
                        "/command-license/v3/api-docs/**",
                        "/auth-license/v3/api-docs/**",
                        "/openapi.yml",     // Archivo YAML de OpenAPI (si lo configuras)
                        "/api/auth-license/register",
                        "/api/auth-license/authenticate","auth-license/v3/api-docs")

                        .permitAll()
                        .anyExchange().authenticated())
                        .addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                        .csrf(ServerHttpSecurity.CsrfSpec::disable)
                        .build();
    }
}
