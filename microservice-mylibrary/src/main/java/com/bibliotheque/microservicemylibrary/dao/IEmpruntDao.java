package com.bibliotheque.microservicemylibrary.dao;

import com.bibliotheque.microservicemylibrary.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface IEmpruntDao extends JpaRepository<Emprunt, Long> {

    //trouver toutes les emprunts par livre
    List<Emprunt>findAll();

    //trouver une reservation par son id
    Optional<Emprunt> findById(Long id);

    //trouver un emprunt par utilisateur
    List<Emprunt> findAllByIdUtilisateur(Long id);

    //sauvegarder un emprunt
    Emprunt save(Optional<Emprunt> reservation);

    //trouve les emprunts non retournées
    List<Emprunt> findAllByDateRetourIsNullAndDateDeFinDuPretBefore(Date dateNow);
}
