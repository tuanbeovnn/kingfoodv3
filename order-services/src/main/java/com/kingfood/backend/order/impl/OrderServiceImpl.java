package com.kingfood.backend.order.impl;

import com.kingfood.backend.DateTimeUtils.DateTimeUtils;
import com.kingfood.backend.convert.Converter;
import com.kingfood.backend.dbprovider.CustomerRepository;
import com.kingfood.backend.dbprovider.OrderDetailRepository;
import com.kingfood.backend.dbprovider.OrderRepository;
import com.kingfood.backend.dbprovider.ProductRepository;
import com.kingfood.backend.domains.dto.OrderDTO;
import com.kingfood.backend.domains.entity.CustomerEntity;
import com.kingfood.backend.domains.entity.OrderDetailsEntity;
import com.kingfood.backend.domains.entity.OrderEntity;
import com.kingfood.backend.domains.entity.ProductEntity;
import com.kingfood.backend.domains.request.OrderDetailRequest;
import com.kingfood.backend.domains.response.OrderResponse;
import com.kingfood.backend.exceptionsv2.AppException;
import com.kingfood.backend.exceptionsv2.ErrorCode;
import com.kingfood.backend.order.OrderService;
import com.kingfood.backend.securityconfig.oath2.service.SecurityUtils;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public OrderResponse createNewOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = Converter.toModel(orderDTO, OrderEntity.class);
        orderEntity.setOrderDate(DateTimeUtils.getDateTimeNow());
        orderEntity = orderRepository.save(orderEntity);
        List<OrderDetailsEntity> orderDetailsEntities = new ArrayList<>();
        for (OrderDetailRequest orderDetailRequest : orderDTO.getOrderDetailRequests()) {
            ProductEntity productEntity = productRepository.findById(orderDetailRequest.getProductId())
                    .orElseThrow(() -> new AppException(ErrorCode.ID_NOT_FOUND));
            validateOrderDetail(orderDetailRequest, productEntity);
            OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
            double percentage = 100 - productEntity.getDiscount();
            double total = percentage * (productEntity.getPrice() * orderDetailRequest.getQuantity()) / 100;
            orderDetailsEntity.setTotal(total);
            orderDetailsEntity.setOrder(orderEntity);
            orderDetailsEntity.setProduct(productEntity);
            orderDetailsEntity.setQuantity(orderDetailRequest.getQuantity());
            orderDetailsEntity.setUnitPrice(productEntity.getPrice());
            orderDetailsEntity.setDiscount(productEntity.getDiscount());
            orderDetailsEntity = orderDetailRepository.save(orderDetailsEntity);
            orderDetailsEntities.add(orderDetailsEntity);
            int quantityLeft = productEntity.getQuantity() - orderDetailRequest.getQuantity();
            productEntity.setQuantity(quantityLeft);
            productEntity = productRepository.save(productEntity);
        }
        orderEntity.setOrderDetails(orderDetailsEntities);
        return Converter.toModel(orderEntity, OrderResponse.class);
    }

    @Override
    @Transactional
    public OrderResponse createOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = Converter.toModel(orderDTO, OrderEntity.class);
        orderEntity.setOrderDate(DateTimeUtils.getDateTimeNow());
        OrderEntity finalOrderEntity = orderEntity;
        List<OrderDetailsEntity> orderDetailsEntities = orderDTO.getOrderDetailRequests().stream().map(item -> {
            OrderDetailsEntity insertData = new OrderDetailsEntity();
            ProductEntity productEntity = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new AppException(ErrorCode.ID_NOT_FOUND));
            validateOrderDetail(item, productEntity);
            double percentage = 100 - productEntity.getDiscount();
            double total = percentage * (productEntity.getPrice() * item.getQuantity()) / 100;
            insertData.setTotal(total);
            insertData.setOrder(finalOrderEntity);
            insertData.setProduct(productEntity);
            insertData.setQuantity(item.getQuantity());
            insertData.setUnitPrice(productEntity.getPrice());
            insertData.setDiscount(productEntity.getDiscount());
            insertData = orderDetailRepository.save(insertData);
            int quantityLeft = productEntity.getQuantity() - item.getQuantity();
            productEntity.setQuantity(quantityLeft);
            productEntity = productRepository.save(productEntity);
            return insertData;
        }).collect(Collectors.toList());
        finalOrderEntity.setOrderDetails(orderDetailsEntities);
        CustomerEntity customerEntity = customerRepository.findById(SecurityUtils.getPrincipal().getUserId()).get();
        orderEntity.setCustomer(customerEntity);
        orderEntity = orderRepository.save(orderEntity);
        return Converter.toModel(orderEntity, OrderResponse.class);
    }

    private void validateOrderDetail(OrderDetailRequest orderDetailRequest, ProductEntity productEntity) {
        if (orderDetailRequest.getQuantity() > productEntity.getQuantity()) {
            throw new AppException(ErrorCode.EMPTY_PRODUCT);
        }
    }
}
