package com.onebucket.global.exeptionManage.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{
    INACTIVE_USER(HttpStatus.FORBIDDEN, "User is inactive"),
    EXPIRED_USER(HttpStatus.FORBIDDEN, "User is expired"),
    BANNED_USER(HttpStatus.FORBIDDEN, "User is banned"),

    USERNAME_PASSWORD_INVALID(HttpStatus.UNAUTHORIZED, "Username or password is invalid");


    private final HttpStatus httpStatus;
    private final String message;
}
