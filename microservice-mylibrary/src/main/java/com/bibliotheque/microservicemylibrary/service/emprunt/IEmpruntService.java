package com.bibliotheque.microservicemylibrary.service.emprunt;

import com.bibliotheque.microservicemylibrary.beans.UtilisateurBean;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IEmpruntService {

    //ajoute 4 semaine à une date
    Date add4Weeks(Date date);

    //affiche une liste d'emprunts pour un utilisateur
    List<Emprunt> findAllByIdUtilisateur(Long id);

    //trouve un emprunt par son id
    Optional<Emprunt> findById(Long id);

    //enregistrer une emprunt
    void save(Emprunt emprunt);

    //trouve les emprunts non retournées par rapport à la date du jour
    List<Emprunt> relance(Date dateNow);

    //trouver un emprunt par copie de livre
    Emprunt findByCopie_Id(Long id);

    //trouver tous les Emprunts pour une copie
    List<Emprunt> findAllByCopie_IdAndDateRetourIsNull(Long id);

    //trouver la liste de tous les emprunts par utilisateur dont la date de retour est null
    List<Emprunt> findAllByIdUtilisateurAndDateRetourIsNull(Long id);

    //prolonger un emprunt
    void prolongerEmprunt(Long id, Long idUtilisateur);

    //retourner un emprunt
    void retournerEmprunt( Long id, Long idUtilisateur);
}