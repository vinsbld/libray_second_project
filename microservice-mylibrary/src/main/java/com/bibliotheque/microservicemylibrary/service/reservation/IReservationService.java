package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {

    //enregistrer une Reservation
    void save(Reservation reservation);

    //trouver toutes les réservations pour un utilisateur
    List<Reservation> findAllByIdUtilisateur(Long id);
}
