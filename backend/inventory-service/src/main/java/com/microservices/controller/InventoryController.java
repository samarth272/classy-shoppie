package com.microservices.controller;

import com.microservices.Entity.Inventory;
import com.microservices.Repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@AllArgsConstructor
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    @GetMapping
    public List<Inventory> getInventory(){
        return inventoryRepository.findAll();
    }
}
