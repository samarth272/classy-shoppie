package com.microservices.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.microservices.dto.ProductEvent;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, ProductEvent> consumerFactory(KafkaProperties kafkaProperties) {

        JsonDeserializer<ProductEvent> deserializer
                = new JsonDeserializer<>(ProductEvent.class);

        deserializer.addTrustedPackages("*");

        Map<String, Object> props = new HashMap<>();

        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                kafkaProperties.getBootstrapServers()
        );

        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "inventory-group"
        );

        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );

        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class
        );

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProductEvent>
            kafkaListenerContainerFactory(  KafkaProperties kafkaProperties) {

        ConcurrentKafkaListenerContainerFactory<String, ProductEvent> factory
                = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(
                consumerFactory(kafkaProperties)
        );

        return factory;
    }
}
