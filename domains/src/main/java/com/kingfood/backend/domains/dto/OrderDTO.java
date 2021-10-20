package com.kingfood.backend.domains.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kingfood.backend.domains.request.OrderDetailRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderDTO extends AbstractDTO{
    private String name;
    private String address;
    private String city;
    private String phone;
    private List<OrderDetailRequest> orderDetailRequests;
}
