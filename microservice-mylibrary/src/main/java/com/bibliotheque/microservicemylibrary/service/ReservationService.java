package com.bibliotheque.microservicemylibrary.service;

import com.bibliotheque.microservicemylibrary.model.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationService {

    Date add4Weeks(Date date);

    List<Reservation> findAllByIdUtilisateur(Long id);


}
