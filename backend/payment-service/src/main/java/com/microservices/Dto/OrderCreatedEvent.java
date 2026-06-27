package com.microservices.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderCreatedEvent {

    private Long orderId;
    private Long userId;
    private Double totalPrice;
    private String status;
    private LocalDateTime createdAt;
}
