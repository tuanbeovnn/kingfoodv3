package com.kingfood.backend.domains.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseRestResponse {
    private int code;
    private boolean success;
    private String message;

    @Builder(buildMethodName = "buildResponse")
    public BaseRestResponse(HttpStatus httpStatus, boolean success, String message) {
        this.code = httpStatus.value();
        this.success = success;
        this.message = message;
    }

}
