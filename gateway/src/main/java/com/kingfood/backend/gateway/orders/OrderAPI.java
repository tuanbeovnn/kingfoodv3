package com.kingfood.backend.gateway.orders;


import com.kingfood.backend.domains.dto.OrderDTO;
import com.kingfood.backend.domains.response.BaseRestResponse;
import com.kingfood.backend.domains.response.OrderBuilderResponse;
import com.kingfood.backend.domains.response.OrderResponse;
import com.kingfood.backend.exceptionsv2.ResourceNotFoundException;
import com.kingfood.backend.order.OrderService;
import com.kingfood.backend.responseBuilder.ResponseEntityBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"Order-Resource"})
@SwaggerDefinition(tags = {
        @Tag(name = "Order-API-Resource", description = "Write description here")
})
@RestController
@RequestMapping(value = "/api")
public class OrderAPI {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/admin/order/create-order", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody OrderDTO orderDto) {
        OrderResponse orderDTO = orderService.createOrder(orderDto);
        return ResponseEntityBuilder.getBuilder()
                .setDetails(orderDTO)
                .setMessage("Create a order successfully")
                .build();
    }

    @RequestMapping(value = "/order/get-order", method = RequestMethod.GET)
    public ResponseEntity<?> getOrder(@RequestParam Long orderId) {
        try {
            OrderBuilderResponse response = orderService.findOrderById(orderId);
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
