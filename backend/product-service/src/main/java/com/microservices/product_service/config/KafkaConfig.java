package com.microservices.product_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic productTopic(){

        return new NewTopic("product-created", 3, (short) 1);           //topic=product-created, partitions=3, replications=1
    }
}
