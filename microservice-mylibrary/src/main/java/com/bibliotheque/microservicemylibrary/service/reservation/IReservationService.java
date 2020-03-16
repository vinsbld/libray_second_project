package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.model.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService {

    Date add4Weeks(Date date);

    List<Reservation> findAllByIdUtilisateur(Long id);


    void addReservation(Long id, Reservation reservation);
}
