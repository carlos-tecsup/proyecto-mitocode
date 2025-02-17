package com.tecsup.microservices.common_models.dto;

import lombok.Data;

@Data
public class UserDTOResponse {
    private String name;
    private String lastname;
    private String email;
    private String username;
    private String[] roles; // ADMIN, USER

    private String token;
}
