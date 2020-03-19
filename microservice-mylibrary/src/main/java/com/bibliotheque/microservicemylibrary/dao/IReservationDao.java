package com.bibliotheque.microservicemylibrary.dao;

import com.bibliotheque.microservicemylibrary.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IReservationDao extends JpaRepository<Reservation, Long> {

    //trouver toutes les reservations par livre
    List<Reservation>findAll();

    //trouver une reservation par son id
    Optional<Reservation> findById(Long id);

    //trouver une reservation par utilisateur
    List<Reservation> findAllByIdUtilisateur(Long id);

    //sauvegarder une reservation
    Reservation save(Optional<Reservation> reservation);
}
