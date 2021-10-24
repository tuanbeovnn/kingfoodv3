package com.kingfood.backend.wallets.api;

import com.kingfood.backend.wallets.dto.response.BaseRestResponse;
import com.kingfood.backend.wallets.dto.response.OrderResponse;
import com.kingfood.backend.wallets.exception.ResourceNotFoundException;
import com.kingfood.backend.wallets.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderApi {

    private final OrderService orderService;

    @Autowired
    public OrderApi(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable String id) {
        try {
            OrderResponse response = orderService.findOrderById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException exception) {
            BaseRestResponse response = BaseRestResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .success(false)
                    .message(exception.getMessage())
                    .buildResponse();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}