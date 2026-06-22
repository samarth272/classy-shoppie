package com.microservices.product_service.service;

import com.microservices.product_service.dto.ProductRequest;
import com.microservices.product_service.dto.ProductResponse;
import com.microservices.product_service.entity.Product;
import com.microservices.product_service.event.ProductCreatedEvent;
import com.microservices.product_service.exception.ProductNotFoundException;
import com.microservices.product_service.mapper.ProductMapper;
import com.microservices.product_service.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductEventProducer productEventProducer;

    @Override
    public Page<ProductResponse> getProducts(int page, int size, String sortBy, String direction){

        Sort sort;

        if(direction.equalsIgnoreCase("desc")){
            sort= Sort.by(sortBy).descending();
        }else{
            sort= Sort.by(sortBy).ascending();
        }

        Pageable pageable= PageRequest.of(page, size, sort);

        return productRepository.findAll(pageable).map(ProductMapper::toResponse);
    }

    @Override
    public ProductResponse saveProduct(ProductRequest request) {
        Product product =
                ProductMapper.toEntity(request);

        Product savedProduct =
                productRepository.save(product);

        //create kafka event
        ProductCreatedEvent event= new ProductCreatedEvent(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getQuantity()
        );

        //publish kafka event
        productEventProducer.sendProductCreatedEvent(event);

        return ProductMapper.toResponse(savedProduct);
    }

    @Override
    @Cacheable(
            value = "products",
            key = "#id"
    )
    public ProductResponse getProductById(Long id) {
        System.out.println("Requested ID : " + id);
        Product product =
                productRepository.findById(id)
                        .orElseThrow(
                                () -> new ProductNotFoundException(
                                        "Product not found with id : " + id
                                )
                        );

        System.out.println("Product found : " + product);
        return ProductMapper.toResponse(product);
    }

    @Override
    @CacheEvict(
            value="products",
            key="#id"
    )
    public ProductResponse updateProduct(Long id, ProductRequest request){

        Product product =
                productRepository.findById(id)
                        .orElseThrow(
                                () -> new ProductNotFoundException(
                                        "Product not found with id : "+id
                                )
                        );

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        Product updated =
                productRepository.save(product);

        return ProductMapper.toResponse(updated);
    }

    @Override
    @CacheEvict(
            value="products",
            key="#id"
    )
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                        .orElseThrow(
                                () -> new ProductNotFoundException(
                                        "Product not found"
                                )
                        );

        productRepository.delete(product);
    }
}
