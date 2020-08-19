package com.bibliotheque.microservicemylibrary.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LivresNotFoundException extends RuntimeException {
    public LivresNotFoundException(String message) {
        super(message);
    }
}
