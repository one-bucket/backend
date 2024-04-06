package com.onebucket.global.exeptionManage.exceptionHandler;

import com.onebucket.global.exeptionManage.errorCode.CommonErrorCode;
import com.onebucket.global.exeptionManage.errorCode.ErrorCode;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.onebucket.global.exeptionManage.exceptionHandler.ErrorResponseGenerator.generateResponse;

@Order(1)
@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
        return generateResponse(ex, errorCode);
    }
}
