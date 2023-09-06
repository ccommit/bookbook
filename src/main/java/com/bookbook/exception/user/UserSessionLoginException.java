package com.bookbook.exception.user;

public class UserSessionLoginException extends RuntimeException {
    public UserSessionLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
