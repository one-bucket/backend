package com.onebucket.global.exeptionManage.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum JwtErrorCode implements ErrorCode{

    EXPIRED_TOKEN("1100"),
    INVALID_SIGNATURE_TOKEN("1101"),
    INVALID_CLAIM_TOKEN("1102"),
    INVALID_FORMED_TOKEN("1103"),
    AUTHORITY_NOT_FOUND_TOKEN("1104"),
    UNSUPPORTED_JWT_ERROR("1199");



    private final String code;

    @Override
    public String getMessage() {
        return null;
    }
    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }
    @Override
    public String getType() {
        return "JWT_ERROR";
    }

}
