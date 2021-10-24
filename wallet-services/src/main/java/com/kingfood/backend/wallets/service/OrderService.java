package com.kingfood.backend.wallets.service;


import com.kingfood.backend.wallets.dto.response.OrderResponse;

public interface OrderService {

    public OrderResponse findOrderById(String id);

}
