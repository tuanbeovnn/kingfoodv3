package com.kingfood.backend.domains.dto;

import com.kingfood.backend.domains.response.CustomerUserResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO extends AbstractDTO {
    private String address;
    private String city;
    private String phone;
    private CustomerUserResponse user;
}
