package com.kingfood.backend.domains.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductProfileResponse {
    private Long id;
    private String productName;
    private String imgHighlight;
    private String image;
    private double price;
    private String size;
    private String description;
    private String nutrition;
    private int purchases;

    public String[] getImage() {
        return StringUtils.isEmpty(image) ? new String[]{} : image.split(";");
    }
}
