package com.project.forumapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MatterNotFoundException extends RuntimeException {

    public MatterNotFoundException(String message) {
        super(message);
    }

}
