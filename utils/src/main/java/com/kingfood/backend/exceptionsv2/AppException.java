package com.kingfood.backend.exceptionsv2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;


/**
 * @Author Tuan Nguyen
 */
@Getter
@Setter
public class AppException extends RuntimeException {

    private String code;

    private String message;

    private HttpStatus status;

    public AppException(HttpStatus code, String message, Map<String, Object> details) {
        super();
    }

    public AppException(ErrorCode code) {
        super();
        this.code = code.code();
        this.message = code.message();
        this.status = code.status();
    }

    public AppException(String message) {
        super(message);
    }
}
