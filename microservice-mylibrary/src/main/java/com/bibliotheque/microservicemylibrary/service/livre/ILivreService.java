package com.bibliotheque.microservicemylibrary.service.livre;

import com.bibliotheque.microservicemylibrary.model.Livre;
import org.springframework.data.repository.query.Param;

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

}
