package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.model.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IReservationService {

    Date add4Weeks(Date date);

    List<Reservation> findAllByIdUtilisateur(Long id);

    Optional<Reservation> findById(Long id);

    void save(Reservation reservation);
}
