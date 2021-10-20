package com.kingfood.backend.domains.response;

import com.kingfood.backend.domains.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    private Long id;
    private String name;
    private String code;
}
