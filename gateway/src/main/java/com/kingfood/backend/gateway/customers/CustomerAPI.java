package com.kingfood.backend.gateway.customers;

import com.kingfood.backend.customer.CustomerService;
import com.kingfood.backend.domains.dto.CustomerDTO;
import com.kingfood.backend.domains.request.CustomerRequest;
import com.kingfood.backend.domains.request.UserRequest;
import com.kingfood.backend.domains.response.ProfileResponse;
import com.kingfood.backend.domains.response.UserResponse;
import com.kingfood.backend.responseBuilder.ResponseEntityBuilder;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @Author: Tuan Nguyen
 */
@Api(
        tags = "Customer-API",
        description = "Providing api for Customer"
)
@RestController
@RequestMapping("/api")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customer/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody @Valid CustomerRequest customerRequest) {
        CustomerDTO output = customerService.createCustomer(customerRequest);
        return ResponseEntityBuilder.getBuilder().setMessage("Create Customer Successfully").setDetails(output).build();
    }

    @RequestMapping(value = "/customer/profile", method = RequestMethod.GET)
    public ResponseEntity<?> profile() {
        ProfileResponse profileResponse = customerService.userProfile();
        return ResponseEntityBuilder.getBuilder().setDetails(profileResponse).setMessage("Get user profile Successfully").build();
    }
}
