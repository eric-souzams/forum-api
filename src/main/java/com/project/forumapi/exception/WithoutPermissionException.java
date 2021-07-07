package com.project.forumapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class WithoutPermissionException extends RuntimeException {

    public WithoutPermissionException(String message) {
        super(message);
    }

}
