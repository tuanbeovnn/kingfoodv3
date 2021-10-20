package com.kingfood.backend.domains.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String productName;
    private String imgHighlight;
    private String image;
    private double price;
    private String size;
    private String description;
    private String nutrition;
    private int quantity;
    private String status;

    public String[] getImage() {
        return StringUtils.isEmpty(image) ? new String[]{} : image.split(";");
    }
}
