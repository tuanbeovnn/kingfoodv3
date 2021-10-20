package com.kingfood.backend.domains.request;

import com.kingfood.backend.domains.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
    private String name;
    private String code;
}
