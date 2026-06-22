package com.microservices.product_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreatedEvent {

    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;
}
