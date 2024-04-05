package com.onebucket.global.exeptionManage.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DataAccessErrorCode implements ErrorCode{
    ENTITY_NOT_FOUND("3000", HttpStatus.NOT_FOUND),
    CONSTRAINT_VIOLATION("3001", HttpStatus.CONFLICT),
    DATA_SEARCH_FAILURE("3002", HttpStatus.NOT_FOUND),
    UNSUPPORTED_DATA_ACCESS_ERROR("3099", HttpStatus.INTERNAL_SERVER_ERROR)
    ;

    private final String code;
    private final HttpStatus httpStatus;

    @Override
    public String getType() {
        return "DATABASE_ERROR";
    }
    public String getMessage() {
        return null;
    }
}
