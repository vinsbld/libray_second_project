package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.dto.ReservationDTO;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.model.StateEnum;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface IReservationService {

    //enregistrer une Reservation
    void save(Reservation reservation);

    //supprimer une Reservation
    void deleteById(Long id);

    //trouver une reservation par son id
    Optional<Reservation> findById(Long id);

    //trouver toutes les réservations pour un utilisateur
    List<Reservation> findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(Long id, StateEnum stateEnum);

    //trouver toutes les réservations d'un livre
    List<Reservation> findAllByLivreAndStateEnumsOrderByDateDeReservationAsc(Livre livre, StateEnum stateEnum);

    //trouver une reservation pour un livre
    List<Reservation> findByLivreAndStateEnumsOrderByDateDeReservationAsc(Livre livre, StateEnum stateEnum);

    //trouver les reservations dont le mail de reservation à été envoyer
    List<Reservation> findByEmailEnvoyerAndStateEnums(Boolean emailEnvoyer, StateEnum stateEnum);

    //trouver toutes les reservations
    List<Reservation> findAll();

    //faire une réservation
    void reserver(Long id, Long idUtilisateur);

    //annuler une reservation
    void annuler(Long id, Long idUtilisateur);

    //afficher les réservations pour un utilisateur
    List<ReservationDTO> afficherlesReservations(Long id);

    //afficher toutes les réservations
    List<Reservation> toutesLesReservations();
}