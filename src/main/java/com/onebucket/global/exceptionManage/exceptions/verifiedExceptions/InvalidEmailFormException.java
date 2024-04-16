package com.onebucket.global.exceptionManage.exceptions.verifiedExceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidEmailFormException extends VerifiedException {
    public InvalidEmailFormException(String message) {
        super(message);
    }

    public InvalidEmailFormException(String message, Throwable cause) {
        super(message, cause);
    }
}