package com.onebucket.global.exceptionManage.exceptionHandler;

import com.onebucket.global.exceptionManage.ErrorResponse;
import com.onebucket.global.exceptionManage.errorCode.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;

import java.util.List;
import java.util.stream.Collectors;

public class ErrorResponseGenerator {

    public static ResponseEntity<Object> generateResponse(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode));
    }

    public static ResponseEntity<Object> generateResponse(ErrorCode errorCode, String message) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode, message));
    }

    public static ResponseEntity<Object> generateResponse(BindException e, ErrorCode errorCode) {
            return ResponseEntity.status(errorCode.getHttpStatus())
                    .body(makeErrorResponse(e, errorCode));
    }

    public static ErrorResponse makeErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .type(errorCode.getType())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }


    public static ErrorResponse makeErrorResponse(ErrorCode errorCode, String message) {
        return ErrorResponse.builder()
                .type(errorCode.getType())
                .code(errorCode.getCode())
                .message(message)
                .build();
    }

    public static ErrorResponse makeErrorResponse(BindException e, ErrorCode errorCode) {
        List<ErrorResponse.ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorResponse.ValidationError::of)
                .collect(Collectors.toList());

        return ErrorResponse.builder()
                .type(errorCode.getType())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .errors(validationErrorList)
                .build();
    }
}
