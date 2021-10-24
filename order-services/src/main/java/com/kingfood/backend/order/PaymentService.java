package com.kingfood.backend.order;


import com.kingfood.backend.domains.request.PaymentRequest;
import com.kingfood.backend.domains.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse payment(PaymentRequest request) throws Exception;
}
