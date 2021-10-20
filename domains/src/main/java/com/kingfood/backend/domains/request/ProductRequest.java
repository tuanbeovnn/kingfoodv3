package com.kingfood.backend.domains.request;

import com.kingfood.backend.domains.dto.CategoryDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class ProductRequest {
    private String productName;
    private String imgHighlight;
    private String image;
    private double price;
    private String size;
    private String description;
    private String nutrition;
    private int quantity;
    private String status;
    private Long categoryId;
}
