package com.kingfood.backend.wallets.service;


import com.kingfood.backend.wallets.dto.request.PaymentRequest;
import com.kingfood.backend.wallets.dto.response.PaymentResponse;

public interface PaymentService {

    PaymentResponse payment(PaymentRequest request) throws Exception;

}
