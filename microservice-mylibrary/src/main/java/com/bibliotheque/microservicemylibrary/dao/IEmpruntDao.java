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

    //trouver une reservation par utilisateur
    List<Emprunt> findAllByIdUtilisateur(Long id);

    //sauvegarder une reservation
    Emprunt save(Optional<Emprunt> reservation);

    //trouve les emprunts non retournées
    List<Emprunt> findAllByDateRetourIsNullAndAndDateDeFinEmpruntBefore(Date dateNow);

    //trouver une reservation par copies de livre
    Emprunt findByCopie_Id(Long id);

}
