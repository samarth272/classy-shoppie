package com.microservices.dto;

import lombok.Data;

@Data
public class InventoryResponse {

    private Long id;
    private Long productId;

    private Integer stock;
}
