package com.kingfood.backend.wallets.service.impl;


import com.kingfood.backend.wallets.dto.response.OrderResponse;
import com.kingfood.backend.wallets.exception.ResourceNotFoundException;
import com.kingfood.backend.wallets.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private static final String DEFAULT_DUMMY_ORDER_ID = "order-123";
    private static final String DEFAULT_AMOUNT = "1000000";
    private static final String EMPTY = "";

    private static final String HMAC_SHA256 = "HmacSHA256";

    @Value("${momo.partnerCode}")
    private String partnerCode;

    @Value("${momo.accessKey}")
    private String accessKey;

    @Value("${momo.secretKey}")
    private String secretKey;

    private static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        Formatter formatter = new Formatter(sb);
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return sb.toString();
    }

    @Override
    public OrderResponse findOrderById(String id) {
        if (!DEFAULT_DUMMY_ORDER_ID.equals(id)) {
            log.info("Not found order by id {}.", id);
            throw new ResourceNotFoundException(String.format("Not found order by id %s.", id));
        }

        final String extraData = EMPTY;
        final String amount = DEFAULT_AMOUNT;
        final String notifyUrl = "https://callback.url/notify";
        final String orderId = UUID.randomUUID().toString();
        final String orderInfo = "Developer is pay 1000 for ordering.";
        final String returnUrl = "https://momo.vn/return";
        final String requestId = UUID.randomUUID().toString();

        final String rawSignature = String.format("partnerCode=%s&accessKey=%s&requestId=%s&amount=%s&orderId=%s&orderInfo=%s" +
                        "&returnUrl=%s&notifyUrl=%s&extraData=%s",
                this.partnerCode, this.accessKey, requestId, amount, orderId, orderInfo, returnUrl, notifyUrl, extraData);

        return OrderResponse.builder()
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
