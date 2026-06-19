package com.microservices.product_service.service;

import com.microservices.product_service.dto.ProductRequest;
import com.microservices.product_service.dto.ProductResponse;
import com.microservices.product_service.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Page<ProductResponse> getProducts(int Page, int size, String sortBy, String direction);
    ProductResponse saveProduct(ProductRequest request);
    ProductResponse getProductById(Long id);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
}
