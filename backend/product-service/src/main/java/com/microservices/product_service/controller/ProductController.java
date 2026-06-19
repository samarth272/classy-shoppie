package com.microservices.product_service.controller;

import com.microservices.product_service.dto.ApiResponse;
import com.microservices.product_service.dto.ProductRequest;
import com.microservices.product_service.dto.ProductResponse;
import com.microservices.product_service.entity.Product;
import com.microservices.product_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductResponse> getProducts( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String direction){
        return productService.getProducts(
                page,
                size,
                sortBy,
                direction
        );
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    public ProductResponse saveProduct(@Valid @RequestBody ProductRequest request){
        return productService.saveProduct(request);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest request){
        return productService.updateProduct(id,request);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteProduct(@PathVariable Long id){

        productService.deleteProduct(id);
        return new ApiResponse("Product deleted successfully!");
    }
}
