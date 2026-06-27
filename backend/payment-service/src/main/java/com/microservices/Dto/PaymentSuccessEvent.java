package com.microservices.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentSuccessEvent {

    private Long orderId;
    private String paymentStatus;
    private LocalDateTime paymentTime;
}
