package com.kingfood.backend.customer;

import com.kingfood.backend.domains.dto.CustomerDTO;
import com.kingfood.backend.domains.request.CustomerRequest;
import com.kingfood.backend.domains.response.ProfileResponse;
import com.kingfood.backend.domains.response.UserResponse;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerRequest customerRequest);
    ProfileResponse userProfile();

}
