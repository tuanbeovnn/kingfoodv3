package com.kingfood.backend.exceptionsv2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @Author Tuan Nguyen
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(AppException.class)
    public Object handleException(HttpServletRequest request, AppException re)
            throws IOException {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(re.getCode());
        errorResponse.setMessage(re.getMessage());
        return new ResponseEntity<>(errorResponse, re.getStatus());
    }

}


