package com.microservices.Service;

import com.microservices.Constant.KafkaTopic;
import com.microservices.dto.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public void publishOrderCreated(OrderCreatedEvent event){

        kafkaTemplate.send(KafkaTopic.ORDER_CREATED, event.getOrderId().toString(), event);
    }
}
