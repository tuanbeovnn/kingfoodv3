package com.kingfood.backend.domains.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Setter
@Getter
@Builder
public class UserRequest {
    private String userName;
    private String password;
    private String email;
    private List<Long> roleIds;
}
