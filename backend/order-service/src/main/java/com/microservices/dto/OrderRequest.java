package com.microservices.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long productId;

    @Positive
    private Integer quantity;
}
