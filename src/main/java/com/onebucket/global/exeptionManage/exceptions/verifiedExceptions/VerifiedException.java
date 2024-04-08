package com.onebucket.global.exeptionManage.exceptions.verifiedExceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VerifiedException extends RuntimeException{
    public VerifiedException(String message) {
        super(message);
    }
    public VerifiedException(String message, Throwable cause) {
        super(message, cause);
    }
}
