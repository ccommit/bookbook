package com.bookbook.exception.user;

public class UserFindingException extends RuntimeException {
    public UserFindingException(String message, Throwable cause) {
        super(message, cause);
    }
}
