package com.bibliotheque.microservicemylibrary.dao;

import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.model.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IReservationDao extends JpaRepository<Reservation, Long> {

    //sauvegarder une Reservation
    Reservation save(Optional<Reservation> reservation);

    //supprimer une Reservation
    void deleteById(Long id);

    //trouver toutes les reservations
    List<Reservation> findAll();

    //trouver une reservation par son id
    Optional<Reservation> findById(Long id);

    //trouver toutes les réservations pour un utilisateur
    List<Reservation> findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(Long id, StateEnum stateEnum);

    //trouver toutes les réservations d'un livre
    List<Reservation> findAllByLivreAndStateEnumsOrderByDateDeReservationAsc(Livre livre, StateEnum stateEnum);

    //trouver une reservation pour un livre
    List<Reservation> findByLivreAndStateEnumsOrderByDateDeReservationAsc(Livre livre, StateEnum stateEnum);

    //trouver les reservations dont le mail de reservation à été envoyer ou non et par status
    List<Reservation> findByEmailEnvoyerAndStateEnums(Boolean emailEnvoyer, StateEnum stateEnum);
}