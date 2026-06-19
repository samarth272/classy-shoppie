package com.microservices.product_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    private String name;

    @Positive(message = "Price must be greater than zero")
    private Double price;

    @PositiveOrZero(message = "Quantity cannot be negative")
    private Integer quantity;
}
