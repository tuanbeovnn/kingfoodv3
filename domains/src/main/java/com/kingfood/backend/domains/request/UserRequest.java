package com.kingfood.backend.domains.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class UserRequest {
    @NotEmpty(message = "Name is required")
    private String userName;
    @NotEmpty(message = "Password is required")
    private String password;
    @NotEmpty(message = "Email is required")
    private String email;
    @NotEmpty(message = "Role is required")
    private List<Long> roleIds;
}
