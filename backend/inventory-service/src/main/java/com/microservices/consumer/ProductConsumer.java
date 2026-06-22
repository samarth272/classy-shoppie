package com.microservices.consumer;

import com.microservices.Entity.Inventory;
import com.microservices.Repository.InventoryRepository;
import com.microservices.dto.ProductEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductConsumer {

    private final InventoryRepository repository;

    @KafkaListener(topics = "product-created", groupId = "inventory-group")
    public void consume(ProductEvent event){

        System.out.println("Product received : " + event.getName());

        Inventory inventory = new Inventory();
        inventory.setProductId(event.getId());
        inventory.setStock(event.getQuantity());
        repository.save(inventory);
    }
}
