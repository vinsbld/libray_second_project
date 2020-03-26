package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.model.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IReservationService {

    //ajoute 4 semaine à une date
    Date add4Weeks(Date date);

    //affiche une liste de reservation pour un utilisateur
    List<Reservation> findAllByIdUtilisateur(Long id);

    //trouve une reservation par son id
    Optional<Reservation> findById(Long id);

    //enregistre une reservation
    void save(Reservation reservation);

    //trouve les reservations non retournées par rapport à la date du jour
    List<Reservation> relance(Date dateNow);
}
