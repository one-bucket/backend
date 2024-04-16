package com.onebucket.global.exceptionManage.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{
    LOCKED_USER("1001",HttpStatus.FORBIDDEN),
    EXPIRED_USER("1002", HttpStatus.FORBIDDEN),
    BANNED_USER("1003", HttpStatus.FORBIDDEN),

    USERNAME_PASSWORD_INVALID("1004",HttpStatus.UNAUTHORIZED),
    CREDENTIALS_EXPIRED("1005", HttpStatus.UNAUTHORIZED),

    UNSUPPORTED_ACCOUNT_EXCEPTION("1099", HttpStatus.INTERNAL_SERVER_ERROR)
    ;

    private final String code;
    private final HttpStatus httpStatus;

    @Override
    public String getType() {
        return "AUTHENTICATION_ERROR";
    }

    @Override
    public String getMessage() {
        return null;
    }
}
