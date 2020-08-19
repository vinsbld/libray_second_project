package com.bibliotheque.microservicemyclient.exeptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

public class CustumErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.status()==404){
            return new LivresNotFoundException("livre non trouvé");
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            ApiError error = mapper.readValue(response.body().asInputStream(), ApiError.class);
            switch (error.getMessage()){
                case"cannotBorrowException01":
                    return new CannotAddBorrowingException("Emprunt impossible, vous avez  déjà un emprunt en cours pour cet ouvrage");

                case"cannotBookingException01":
                    return new CannotAddBookingException("réservation impossible, vous avez déjà une réservation en cours pour cet ouvrage");

                case"cannotBookingException02":
                    return new CannotAddBookingException("réservation impossible, vous avez déjà un emprunt en cours pour cet ouvrage");

                case"cannotBookingException03":
                    return new CannotAddBookingException("réservation impossible, la liste des réservations pour cet ouvrage est compléte");

                case"CannotExtendBorrowingException01":
                    return new CannotExtendBorrowingException("impossible de prolonger le prêt, délais dépassé");

                case"CannotExtendBorrowingException02":
                    return new CannotExtendBorrowingException("impossible de prolonger le prêt, le prêt a déjà été prolongé");

                default: return defaultErrorDecoder.decode(methodKey, response);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }
}
