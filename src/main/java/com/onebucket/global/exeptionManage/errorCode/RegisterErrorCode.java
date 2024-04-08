package com.onebucket.global.exeptionManage.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RegisterErrorCode implements ErrorCode{
    UNSUPPORTED_SCHOOL("1200", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL_FORM("1201", HttpStatus.BAD_REQUEST),
    INVALID_VERIFIED_CODE("1202", HttpStatus.BAD_REQUEST),
    UNSUPPORTED_VERIFIED_ERROR("1299", HttpStatus.INTERNAL_SERVER_ERROR)
    ;

    private final String code;
    private final HttpStatus httpStatus;



    @Override
    public String getType() {
        return "REGISTER_ERROR";
    }

    @Override
    public String getMessage() {
        return null;
    }
}
