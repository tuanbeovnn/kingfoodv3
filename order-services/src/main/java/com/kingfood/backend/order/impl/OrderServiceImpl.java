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
import com.kingfood.backend.domains.response.OrderBuilderResponse;
import com.kingfood.backend.domains.response.OrderResponse;
import com.kingfood.backend.exceptionsv2.AppException;
import com.kingfood.backend.exceptionsv2.ErrorCode;
import com.kingfood.backend.exceptionsv2.ResourceNotFoundException;
import com.kingfood.backend.order.OrderService;
import com.kingfood.backend.securityconfig.oath2.service.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private static final String EMPTY = "";
    private static final String NOTIFY_URL = "https://callback.url/notify";
    private static final String RETURN_URL = "https://momo.vn/return";
    private static final String HMAC_SHA256 = "HmacSHA256";

    @Value("${momo.partnerCode}")
    private String partnerCode;

    @Value("${momo.accessKey}")
    private String accessKey;

    @Value("${momo.secretKey}")
    private String secretKey;

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

    private static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        Formatter formatter = new Formatter(sb);
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return sb.toString();
    }

    @Override
    public OrderBuilderResponse findOrderById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Not found order by id %s.", id)));
        final String extraData = EMPTY;
        final String amount = orderEntity.getOrderDetails().stream().mapToLong(item -> item.getTotal().longValue()).sum() + EMPTY;
        final String notifyUrl = NOTIFY_URL;
        final String orderId = UUID.randomUUID().toString();
        final String orderInfo = String.format("Customer needs to pay %s for ordering.", amount);
        final String returnUrl = RETURN_URL;
        final String requestId = UUID.randomUUID().toString();
        final String rawSignature = String.format("partnerCode=%s&accessKey=%s&requestId=%s&amount=%s&orderId=%s&orderInfo=%s" +
                        "&returnUrl=%s&notifyUrl=%s&extraData=%s",
                this.partnerCode, this.accessKey, requestId, amount, orderId, orderInfo, returnUrl, notifyUrl, extraData);
        return OrderBuilderResponse.builder()
                .partnerCode(this.partnerCode)
                .accessKey(this.accessKey)
                .requestId(requestId)
                .orderId(orderId)
                .amount(amount)
                .orderInfo(orderInfo)
                .extraData(extraData)
                .signature(generateSignature(rawSignature))
                .redirectUrl(returnUrl)
                .ipnUrl(notifyUrl)
                .requestType("captureMoMoWallet")
                .build();
    }

    private String generateSignature(String rawData) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(this.secretKey.getBytes(), HMAC_SHA256);
            Mac mac = Mac.getInstance(HMAC_SHA256);
            mac.init(secretKeySpec);
            byte[] rawHmac = mac.doFinal(rawData.getBytes(StandardCharsets.UTF_8));
            return toHexString(rawHmac);
        } catch (NoSuchAlgorithmException | InvalidKeyException exception) {
            exception.printStackTrace();
        }
        return EMPTY;
    }
}
