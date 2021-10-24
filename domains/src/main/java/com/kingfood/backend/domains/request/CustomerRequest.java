package com.kingfood.backend.domains.request;

import com.kingfood.backend.domains.dto.CustomerUserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {
    private String address;
    private String city;
    private String phone;
    private CustomerUserDTO user;
}
