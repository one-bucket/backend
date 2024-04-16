package com.onebucket.global.exceptionManage.exceptions.verifiedExceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidVerifiedCodeException extends VerifiedException {
    public InvalidVerifiedCodeException(String message) {
        super(message);
    }

    public InvalidVerifiedCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}