package com.bookbook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserIdNotExistsException extends RuntimeException {
    public UserIdNotExistsException(String message) {
        super(message);
    }
}
