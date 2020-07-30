package com.bibliotheque.microservicemylibrary.dao;

import com.bibliotheque.microservicemylibrary.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IReservationDao extends JpaRepository<Reservation, Long> {

    //sauvegarder une Reservation
    Reservation save(Optional<Reservation> reservation);

    //trouver toutes les réservations pour un utilisateur
    List<Reservation> findAllByIdUtilisateur(Long id);
}
