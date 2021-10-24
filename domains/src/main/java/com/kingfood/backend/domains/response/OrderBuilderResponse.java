package com.kingfood.backend.domains.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderBuilderResponse {
    private String partnerCode;
    private String accessKey;
    private String requestId;
    private String orderId;
    private String amount;
    private String orderInfo;
    private String requestType;
    @JsonProperty("returnUrl")
    private String redirectUrl;
    @JsonProperty("notifyUrl")
    private String ipnUrl;
    private String extraData;
    private String signature;
}

