package com.microservices.product_service.controller;

import com.microservices.product_service.entity.Product;
import com.microservices.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @PostMapping
    public  Product save(@RequestBody Product product){
        return productRepository.save(product);
    }
}
