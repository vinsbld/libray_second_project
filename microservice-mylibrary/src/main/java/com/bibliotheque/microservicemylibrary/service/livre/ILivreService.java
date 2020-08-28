package com.bibliotheque.microservicemylibrary.service.livre;

import com.bibliotheque.microservicemylibrary.model.Livre;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

public interface ILivreService {

    //trouve tous les livres
    List<Livre>findAll();

    //trouve un livre par son id
    Optional<Livre> findById(Long id);

    //enregistre un livre
    Livre save(Livre livre);

    //trouve un livre par son titre lors d'une recherche
    List<Livre> chercherParTitre(@Param("x") String motCle);

    //trouve un livre par l'id d'une copie
    Optional<Livre> findByCopiesId(Long id);

    //trouve la date de retour la plus proche
    void dateDeRetourLaplusProche(Livre livre);

    //affiche la liste des livres
    List<Livre> livres();

    //affiche le détail d'un livre
    Optional<Livre> livre(Long id);
}