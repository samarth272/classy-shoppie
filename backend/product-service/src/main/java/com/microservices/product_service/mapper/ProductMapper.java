package com.microservices.product_service.mapper;

import com.microservices.product_service.dto.ProductRequest;
import com.microservices.product_service.dto.ProductResponse;
import com.microservices.product_service.entity.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequest request){

        Product product = new Product();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        return product;
    }

    public static ProductResponse toResponse(Product product){

        ProductResponse response= new ProductResponse();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());

        return response;
    }
}
