package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.model.Reservation;

import java.util.List;

public interface IReservationService {

    //enregistrer une Reservation
    void save(Reservation reservation);

    //trouver toutes les réservations pour un utilisateur
    List<Reservation> findAllByIdUtilisateur(Long id);

    //trouver toutes les réservations d'une copie
    List<Reservation>
    findAllByCopie_IdOrderByDateDeReservationAsc(Long id);

}
