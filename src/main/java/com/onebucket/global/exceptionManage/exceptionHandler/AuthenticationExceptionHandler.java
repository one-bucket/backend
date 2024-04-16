package com.onebucket.global.exceptionManage.exceptionHandler;

import com.onebucket.global.exceptionManage.errorCode.ErrorCode;
import com.onebucket.global.exceptionManage.errorCode.UserErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.onebucket.global.exceptionManage.exceptionHandler.ErrorResponseGenerator.generateResponse;

@Order(1)
@RestControllerAdvice
@Slf4j
public class AuthenticationExceptionHandler {

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<Object> handleSignInException(AuthenticationException ex) {
        ErrorCode errorCode = UserErrorCode.USERNAME_PASSWORD_INVALID;
        return generateResponse(errorCode, ex.getMessage());
    }

    @ExceptionHandler(AccountExpiredException.class)
    public ResponseEntity<Object> handleAccountExpiredException(AccountExpiredException ex) {
        ErrorCode errorCode = UserErrorCode.EXPIRED_USER;
        return generateResponse(errorCode, ex.getMessage());
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<Object> handleCredentialsExpiredException(CredentialsExpiredException ex) {
        ErrorCode errorCode = UserErrorCode.CREDENTIALS_EXPIRED;
        return generateResponse(errorCode, ex.getMessage());
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<Object> handleLockedException(LockedException ex) {
        ErrorCode errorCode = UserErrorCode.LOCKED_USER;
        return generateResponse(errorCode, ex.getMessage());
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<Object> handleDisabledException(CredentialsExpiredException ex) {
        ErrorCode errorCode = UserErrorCode.BANNED_USER;
        return generateResponse(errorCode, ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleDisabledException(AuthenticationException ex) {
        ErrorCode errorCode = UserErrorCode.UNSUPPORTED_ACCOUNT_EXCEPTION;
        return generateResponse(errorCode, ex.getMessage());
    }
}
