package com.kingfood.backend.domains.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerUserDTO {
    private String email;
    private String username;
    private String password;
    private String fullName;
}
