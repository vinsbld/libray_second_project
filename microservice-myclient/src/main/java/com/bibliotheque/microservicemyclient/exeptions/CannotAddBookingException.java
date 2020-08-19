package com.bibliotheque.microservicemyclient.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CannotAddBookingException extends RuntimeException{
    public CannotAddBookingException(String message){
        super(message);
    }
}