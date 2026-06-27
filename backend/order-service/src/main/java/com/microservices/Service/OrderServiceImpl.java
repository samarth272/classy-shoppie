package com.microservices.Service;

import com.microservices.Client.InventoryClient;
import com.microservices.Client.ProductClient;
import com.microservices.Entity.Order;
import com.microservices.Entity.OrderStatus;
import com.microservices.Exception.OrderException;
import com.microservices.Repository.OrderRepository;
import com.microservices.dto.InventoryResponse;
import com.microservices.dto.OrderCreatedEvent;
import com.microservices.dto.OrderRequest;
import com.microservices.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository repository;

    private final ProductClient productClient;

    private final InventoryClient inventoryClient;

    private final OrderEventPublisher orderEventPublisher;

    @Override
    public Order createOrder(OrderRequest request){

        ProductResponse product= productClient.getProduct(request.getProductId());

        if (product == null){
            throw new OrderException("Product not found");
        }

        InventoryResponse inventory = inventoryClient.getInventory(request.getProductId());

        if(inventory == null){
            throw new OrderException(
                    "Inventory not available"
            );
        }

        if (inventory.getStock() < request.getQuantity()){
            throw new OrderException("Insufficient stock");
        }

        Order order= new Order();

        order.setUserId(request.getUserId());
        order.setProductId(product.getId());
        order.setQuantity(request.getQuantity());
        order.setTotalPrice(product.getPrice() * request.getQuantity());

        order.setStatus(OrderStatus.CREATED);

        Order savedOrder = repository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent();

        event.setOrderId(savedOrder.getId());
        event.setUserId(savedOrder.getUserId());
        event.setTotalPrice(savedOrder.getTotalPrice());
        event.setStatus("CREATED");
        event.setCreatedAt(LocalDateTime.now());

        orderEventPublisher.publishOrderCreated(event);

        return savedOrder;
    }
}
