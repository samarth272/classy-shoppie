package com.microservices.Service;

import com.microservices.Entity.Inventory;
import com.microservices.exception.InventoryNotFoundException;
import com.microservices.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory getInventoryByProductId(Long productId) {

        return inventoryRepository
                .findByProductId(productId)
                .orElseThrow(
                        () -> new InventoryNotFoundException(
                                "Inventory not found for product "
                                        + productId
                        )
                );
    }
}
