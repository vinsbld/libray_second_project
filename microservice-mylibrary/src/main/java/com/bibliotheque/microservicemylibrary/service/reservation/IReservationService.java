package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.model.StateEnum;

import java.util.List;

public interface IReservationService {

    //enregistrer une Reservation
    void save(Reservation reservation);

    //trouver toutes les réservations pour un utilisateur
    List<Reservation> findAllByIdUtilisateurOrderByDateDeReservationAsc(Long id);

    //trouver toutes les réservations d'un livre
    List<Reservation> findAllByLivreAndStateEnumsOrderByDateDeReservationAsc(Livre livre, StateEnum stateEnum);

    //trouver une reservation pour un livre
    List<Reservation> findByLivreOrderByDateDeReservationAsc(Livre livre);

}
