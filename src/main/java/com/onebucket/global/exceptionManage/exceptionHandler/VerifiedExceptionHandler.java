package com.onebucket.global.exceptionManage.exceptionHandler;

import com.onebucket.global.exceptionManage.errorCode.ErrorCode;
import com.onebucket.global.exceptionManage.errorCode.RegisterErrorCode;
import com.onebucket.global.exceptionManage.exceptions.verifiedExceptions.InvalidEmailFormException;
import com.onebucket.global.exceptionManage.exceptions.verifiedExceptions.InvalidVerifiedCodeException;
import com.onebucket.global.exceptionManage.exceptions.verifiedExceptions.UnSupportedUniversityException;
import com.onebucket.global.exceptionManage.exceptions.verifiedExceptions.VerifiedException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.onebucket.global.exceptionManage.exceptionHandler.ErrorResponseGenerator.generateResponse;

@Order(1)
@RestControllerAdvice
public class VerifiedExceptionHandler {

    @ExceptionHandler(UnSupportedUniversityException.class)
    public ResponseEntity<Object> handleUnSupportedUniversityException(UnSupportedUniversityException ex) {
        ErrorCode errorCode = RegisterErrorCode.UNSUPPORTED_SCHOOL;
        return generateResponse(errorCode, ex.getMessage());
    }

    @ExceptionHandler(InvalidEmailFormException.class)
    public ResponseEntity<Object> handleInvalidEmailFormException(InvalidEmailFormException ex) {
        ErrorCode errorCode = RegisterErrorCode.INVALID_EMAIL_FORM;
        return generateResponse(errorCode, ex.getMessage());
    }

    @ExceptionHandler(InvalidVerifiedCodeException.class)
    public ResponseEntity<Object> handleInvalidVerifiedCodeException(InvalidVerifiedCodeException ex) {
        ErrorCode errorcode = RegisterErrorCode.INVALID_VERIFIED_CODE;
        return generateResponse(errorcode, ex.getMessage());
    }

    @ExceptionHandler(VerifiedException.class)
    public ResponseEntity<Object> handleVerifiedException(VerifiedException ex) {
        ErrorCode errorcode = RegisterErrorCode.UNSUPPORTED_VERIFIED_ERROR;
        return generateResponse(errorcode, ex.getMessage());
    }
}
