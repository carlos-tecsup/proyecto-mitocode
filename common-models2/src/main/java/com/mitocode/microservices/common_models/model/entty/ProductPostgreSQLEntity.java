package com.mitocode.microservices.common_models.model.entty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductPostgreSQLEntity {

    @Id
    private String productId;
    private String productName;
    private String productType;
    private Long price;
    private Integer stock;


}
