package com.mitocode.microservices.common_models.model.entty;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Builder
@Document("sales")
public class SalesEntity {

    @Id
    private String id;
    private Double amount;
    private String productName;
    private UserEntity user;

}
