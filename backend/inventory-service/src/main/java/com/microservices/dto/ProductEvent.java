package com.microservices.dto;

import lombok.Data;

@Data
public class ProductEvent {

    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
}
