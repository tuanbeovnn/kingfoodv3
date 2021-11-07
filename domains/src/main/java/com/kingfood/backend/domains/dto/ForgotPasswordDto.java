package com.kingfood.backend.domains.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ForgotPasswordDto {
    @NotEmpty(message = "Email is required")
    private String email;
}
