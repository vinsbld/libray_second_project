package com.bibliotheque.microservicemyclient.exeptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustumErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status()==404){
            return new LivresNotFoundException("livre non trouvé");
        }else if (response.status()==406)
            return new CannotAddBookingException("réservation impossible");
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
