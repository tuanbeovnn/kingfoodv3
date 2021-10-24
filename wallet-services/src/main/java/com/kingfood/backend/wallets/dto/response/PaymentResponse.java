package com.kingfood.backend.wallets.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentResponse {
    private String requestId;
    private int errorCode;
    private String orderId;
    private String message;
    private String localMessage;
    private String requestType;
    private String payUrl;
    private String signature;
}
