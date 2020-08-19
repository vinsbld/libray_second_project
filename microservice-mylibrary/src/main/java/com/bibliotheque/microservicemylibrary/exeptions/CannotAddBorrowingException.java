package com.bibliotheque.microservicemylibrary.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CannotAddBorrowingException extends RuntimeException{
    public CannotAddBorrowingException(String message){
        super(message);
    }
}
