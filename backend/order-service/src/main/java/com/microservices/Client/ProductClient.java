package com.microservices.Client;

import com.microservices.Config.FeignConfig;
import com.microservices.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", configuration = FeignConfig.class)
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductResponse getProduct(
            @PathVariable Long id
    );
}
