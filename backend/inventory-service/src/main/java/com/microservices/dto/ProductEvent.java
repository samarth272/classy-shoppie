package com.microservices.dto;

import lombok.Data;

@Data
public class ProductEvent {

    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;
}
