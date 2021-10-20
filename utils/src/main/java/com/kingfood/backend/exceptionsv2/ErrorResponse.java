package com.kingfood.backend.exceptionsv2;

import lombok.Getter;
import lombok.Setter;

/**
 * @author TuanNguyen
 */
@Getter
@Setter
public class ErrorResponse {
    private String code;
    private String message;
}