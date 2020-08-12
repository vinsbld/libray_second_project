package com.bibliotheque.microservicemyclient.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CannotExtendBorrowingException extends RuntimeException {
    public CannotExtendBorrowingException(String message){
        super(message);
    }
}
