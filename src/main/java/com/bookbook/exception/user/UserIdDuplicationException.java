package com.bookbook.exception.user;

public class UserIdDuplicationException extends RuntimeException {
    public UserIdDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
