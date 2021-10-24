package com.kingfood.backend.customer.impl;

import com.kingfood.backend.commons.AppConstants;
import com.kingfood.backend.constants.AppConstant;
import com.kingfood.backend.convert.Converter;
import com.kingfood.backend.convert.StringConvert;
import com.kingfood.backend.customer.CustomerService;
import com.kingfood.backend.dbprovider.CustomerRepository;
import com.kingfood.backend.dbprovider.OrderRepository;
import com.kingfood.backend.dbprovider.UserRepository;
import com.kingfood.backend.dbprovider.custom.ProductRepositoryCustom;
import com.kingfood.backend.domains.dto.CustomerDTO;
import com.kingfood.backend.domains.dto.CustomerUserDTO;
import com.kingfood.backend.domains.entity.CustomerEntity;
import com.kingfood.backend.domains.entity.ProductEntity;
import com.kingfood.backend.domains.entity.UserEntity;
import com.kingfood.backend.domains.request.CustomerRequest;
import com.kingfood.backend.domains.response.ProductProfileResponse;
import com.kingfood.backend.domains.response.ProfileResponse;
import com.kingfood.backend.exceptions.CommonUtils;
import com.kingfood.backend.exceptions.CustomException;
import com.kingfood.backend.exceptionsv2.AppException;
import com.kingfood.backend.exceptionsv2.ErrorCode;
import com.kingfood.backend.securityconfig.oath2.service.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CustomerImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepositoryCustom productRepositoryCustom;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public CustomerDTO createCustomer(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = new CustomerEntity();
        UserEntity user = saveOrUpdateUserForEmployee(customerRequest.getUser());
        customerEntity = Converter.toModel(customerRequest, CustomerEntity.class);
        customerEntity.setUser(user);
        customerEntity.setEmail(user.getEmail());
        customerEntity = customerRepository.save(customerEntity);
        return Converter.toModel(customerEntity, CustomerDTO.class);
    }

    @Override
    public ProfileResponse userProfile() {
        ProfileResponse profileResponse = new ProfileResponse();
        CustomerEntity customerEntity = customerRepository.findById(SecurityUtils.getPrincipal().getUserId()).get();
        Long countOrderCustomer = orderRepository.countByCustomerId(SecurityUtils.getPrincipal().getUserId());
        List<ProductProfileResponse> productProfileResponses = productRepositoryCustom.findListOrderByCustomerId(SecurityUtils.getPrincipal().getUserId());
        profileResponse.setListProduct(productProfileResponses);
        profileResponse.setOrder(countOrderCustomer);
        profileResponse.setPending(0);
        profileResponse.setName(customerEntity.getUserName());
        return profileResponse;
    }

    /**
     * saveUserForEmployee
     *
     * @param user
     * @return
     */
    private UserEntity saveOrUpdateUserForEmployee(CustomerUserDTO user) {
        validateUserCreate(user.getUsername(), user.getEmail());
        UserEntity entity = Converter.toModel(user, UserEntity.class);
        entity.setFullName(StringConvert.convertUpperCaseStringName(user.getFullName()));
        entity.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(entity);
    }

    private void validateUserCreate(String username, String email) {
        UserEntity userValid = userRepository.findOneByEmailOrUserNameAndStatus(email, username, AppConstant.ACTIVE.ACTIVE_STATUS);
        if (userValid != null) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }
    }
}
