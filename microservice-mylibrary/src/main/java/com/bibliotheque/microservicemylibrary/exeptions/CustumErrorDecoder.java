package com.bibliotheque.microservicemylibrary.exeptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustumErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status()==404){
            return new LivresNotFoundExeption("livre non trouvé");
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
