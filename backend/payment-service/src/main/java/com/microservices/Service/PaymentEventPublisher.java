package com.microservices.Service;

import com.microservices.Dto.PaymentSuccessEvent;
import com.microservices.KafkaTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishPaymentSuccess(PaymentSuccessEvent event){

        kafkaTemplate.send(KafkaTopic.PAYMENT_SUCCESS, event.getOrderId().toString(), event);
    }
}
