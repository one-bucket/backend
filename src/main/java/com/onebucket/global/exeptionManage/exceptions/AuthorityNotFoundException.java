package com.onebucket.global.exeptionManage.exceptions;

public class AuthorityNotFoundException extends RuntimeException{
    public AuthorityNotFoundException() {
        super("No authority information found in the token");
    }
    public AuthorityNotFoundException(String message) {
        super(message);
    }
}
