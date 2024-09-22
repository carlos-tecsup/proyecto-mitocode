package com.mitocode.microservices.common_models.model.entty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class ProductEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String productId;
    private String productName;
    private String productType;
    private Long price;
    private Integer stock;

}
