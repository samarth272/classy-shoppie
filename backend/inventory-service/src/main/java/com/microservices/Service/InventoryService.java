package com.microservices.Service;

import com.microservices.Entity.Inventory;

public interface InventoryService {

    Inventory getInventoryByProductId(Long productId);
}
