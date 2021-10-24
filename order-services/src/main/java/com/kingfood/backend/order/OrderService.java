package com.kingfood.backend.order;

import com.kingfood.backend.domains.dto.OrderDTO;
import com.kingfood.backend.domains.response.OrderBuilderResponse;
import com.kingfood.backend.domains.response.OrderResponse;

public interface OrderService {
    OrderResponse createNewOrder(OrderDTO orderDTO);

    OrderResponse createOrder(OrderDTO orderDTO);

    OrderBuilderResponse findOrderById(Long id);
}
