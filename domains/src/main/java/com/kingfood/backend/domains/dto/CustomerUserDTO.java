package com.kingfood.backend.domains.dto;

import com.kingfood.backend.validation.ValidateCustom;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class CustomerUserDTO {
    @ValidateCustom(message = "Email is required")
    private String email;
    @ValidateCustom(message = "UserName is required")
    private String username;
    @ValidateCustom(message = "Password is required")
    private String password;
    private String fullName;
}
