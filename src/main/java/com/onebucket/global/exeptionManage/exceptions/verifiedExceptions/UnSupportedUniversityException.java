package com.onebucket.global.exeptionManage.exceptions.verifiedExceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnSupportedUniversityException extends VerifiedException{
    public UnSupportedUniversityException(String message) {
        super(message);
    }
    public UnSupportedUniversityException(String message, Throwable cause) {
        super(message, cause);
    }
}
