package com.tecsup.microservices.common_models.dto;

import lombok.Builder;
import lombok.Data;

   @Data
   @Builder
public class AuthDTOResponse {
   private UserDTOResponse user;
}
