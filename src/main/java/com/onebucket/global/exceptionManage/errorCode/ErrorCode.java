package com.onebucket.global.exceptionManage.errorCode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String getType();
    String getCode();
    HttpStatus getHttpStatus();
    String getMessage();
}
