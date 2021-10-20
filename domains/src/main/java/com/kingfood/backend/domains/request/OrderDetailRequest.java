package com.kingfood.backend.domains.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailRequest {
    private Long productId;
    private Integer quantity;
}
