package com.microservices.product_service.service;

import com.microservices.product_service.event.ProductCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductEventProducer {

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public void sendProductCreatedEvent(ProductCreatedEvent event) {

        kafkaTemplate.send("product-created", event.getProductId().toString(), event)
                .whenComplete((result, error) -> {
                    if (error != null) {
                        System.out.println(
                                "Kafka publish failed : "
                                        + error.getMessage()
                        );
                    } else {
                        System.out.println(
                                "Event published successfully"
                        );
                    }
                });
    }
}
