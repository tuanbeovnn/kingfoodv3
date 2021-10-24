package com.kingfood.backend.domains.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfileResponse {
    private String name;
    private long order;
    private int pending;
    private List<ProductProfileResponse> listProduct;

}
