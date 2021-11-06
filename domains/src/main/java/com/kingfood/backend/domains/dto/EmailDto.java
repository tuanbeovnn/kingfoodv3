package com.kingfood.backend.domains.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class EmailDto {
    @Email
    private String email;
    @NotNull
    private Long expire;
    @NotNull
    private String signature;
    @NotNull
    private Long id;
}
