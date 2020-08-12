package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.model.Emprunt;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IEmpruntService {

    //ajoute 4 semaine à une date
    Date add4Weeks(Date date);

    //affiche une liste de reservation pour un utilisateur
    List<Emprunt> findAllByIdUtilisateur(Long id);

    //trouve une reservation par son id
    Optional<Emprunt> findById(Long id);

    //enregistre une emprunt
    void save(Emprunt emprunt);

    //trouve les emprunts non retournées par rapport à la date du jour
    List<Emprunt> relance(Date dateNow);
}
