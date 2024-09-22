package com.tecsup.microservices.common_models.dto;


public record UserRegister(
        String id,
        String name,
        String lastname,
        String email,
        String username,
        String password,
        String[] roles
) {

}