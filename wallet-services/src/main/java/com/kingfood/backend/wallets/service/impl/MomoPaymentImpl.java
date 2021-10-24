package com.kingfood.backend.wallets.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingfood.backend.wallets.dto.request.PaymentRequest;
import com.kingfood.backend.wallets.dto.response.PaymentResponse;
import com.kingfood.backend.wallets.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;

@Service("momoPaymentService")
@Slf4j
public class MomoPaymentImpl implements PaymentService {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final int ERROR_CODE = 300;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;
    @Value("${momo.environment}")
    private String environment;
    @Value("${momo.baseUrl}")
    private String baseUrl;

    @Autowired
    public MomoPaymentImpl(@Qualifier("okHttpClient") OkHttpClient okHttpClient,
                           @Qualifier("objectMapper") ObjectMapper objectMapper) {
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public PaymentResponse payment(PaymentRequest request) {
        log.info("Process in {} environment.", environment);
        return requestTransactionToMomo(request);
    }

    private PaymentResponse requestTransactionToMomo(PaymentRequest request) {
//        try {
//            final String requestJson = objectMapper.writeValueAsString(request);
//            RequestBody requestBody = RequestBody.create(JSON, requestJson);
//            Request httpRequest = new Request.Builder()
//                    .url(this.baseUrl + "/gw_payment/transactionProcessor")
//                    .post(requestBody)
//                    .build();
//            Response httpResponse = okHttpClient.newCall(httpRequest).execute();
//            if (httpResponse.code() >= ERROR_CODE) {
//                httpResponse.close();
//            }
//            Assert.notNull(httpResponse.body(), "response is null!");
//            final String responseJson = httpResponse.body().string();
//            PaymentResponse response = objectMapper.readValue(responseJson, PaymentResponse.class);
//            log.info("Momo payment response {}.", response.toString());
//            return response;
//        } catch (JsonProcessingException exception) {
//            log.error("Parse request with error {}.", exception.getMessage());
//        } catch (IOException exception) {
//            log.error("Execute request with error {}.", exception.getMessage());
//        }
        return null;
    }

}