package com.microservices.Consumer;

import com.microservices.Dto.OrderCreatedEvent;
import com.microservices.Dto.PaymentSuccessEvent;
import com.microservices.Entity.Payment;
import com.microservices.Repository.PaymentRepository;
import com.microservices.Service.PaymentEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final PaymentRepository paymentRepository;

    private final PaymentEventPublisher publisher;

    @KafkaListener(
            topics = "order-created",
            groupId = "payment-group"
    )
    public void consume(OrderCreatedEvent event){

        System.out.println("Processing payment for order "+event.getOrderId());

        Payment payment = new Payment();

        payment.setOrderId(event.getOrderId());
        payment.setUserId(event.getUserId());
        payment.setAmount(event.getTotalPrice());

        // dummy payment processing
        payment.setStatus("SUCCESS");

        paymentRepository.save(payment);

        PaymentSuccessEvent successEvent = new PaymentSuccessEvent();

        successEvent.setOrderId(event.getOrderId());
        successEvent.setPaymentStatus("SUCCESS");
        successEvent.setPaymentTime(LocalDateTime.now());

        publisher.publishPaymentSuccess(successEvent);
    }
}
