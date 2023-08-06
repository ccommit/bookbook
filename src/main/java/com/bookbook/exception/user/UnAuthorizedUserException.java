package com.bookbook.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UnAuthorizedUserException extends RuntimeException {
    public UnAuthorizedUserException(String message) {
        super(message);
    }
}
