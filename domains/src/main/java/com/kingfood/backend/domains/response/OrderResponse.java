package com.kingfood.backend.domains.response;

import com.kingfood.backend.domains.dto.OrderDetailsDTO;
import com.kingfood.backend.domains.request.OrderDetailRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private String name;
    private String address;
    private String city;
    private String phone;
    private List<OrderDetailsDTO> orderDetails;
}
