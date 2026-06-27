package com.microservices.Service;

import com.microservices.Entity.Order;
import com.microservices.dto.OrderRequest;

public interface OrderService {

    Order createOrder(OrderRequest request);
}
