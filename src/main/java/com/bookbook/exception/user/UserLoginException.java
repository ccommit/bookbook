package com.bookbook.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserLoginException extends RuntimeException {
    public UserLoginException(String message) {
        super(message);
    }
}
