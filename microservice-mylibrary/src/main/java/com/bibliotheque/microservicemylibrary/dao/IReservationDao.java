package com.bibliotheque.microservicemylibrary.dao;

import com.bibliotheque.microservicemylibrary.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IReservationDao extends JpaRepository<Reservation, Long> {

    //trouver une reservation par utilisateur
    List<Reservation> findAllByIdUtilisateur(Long id);

    //sauvegarder une reservation
    Reservation save(Reservation reservation);


    void save(Long id, Reservation reservation);
}
