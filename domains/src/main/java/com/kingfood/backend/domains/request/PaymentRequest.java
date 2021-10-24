package com.kingfood.backend.domains.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentRequest {
    private String partnerCode;
    private String accessKey;
    private String requestId;
    private String orderId;
    private String amount;
    private String orderInfo;
    private String requestType;
    private String returnUrl;
    private String notifyUrl;
    private String extraData;
    private String signature;
}
