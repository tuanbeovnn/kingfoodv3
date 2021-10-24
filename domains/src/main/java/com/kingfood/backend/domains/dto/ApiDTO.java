package com.kingfood.backend.domains.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ApiDTO extends AbstractDTO {
    private String name;
    private String httpMethod;
    private String pattern;
    private Boolean isRequiredAccessToken;
    private Boolean shouldCheckPermission;
    private List<Long> roleIds;
}
