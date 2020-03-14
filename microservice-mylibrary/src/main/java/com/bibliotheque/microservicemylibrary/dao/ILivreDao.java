package com.bibliotheque.microservicemylibrary.dao;

import com.bibliotheque.microservicemylibrary.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface
ILivreDao extends JpaRepository<Livre, Long> {

    //recupere la liste de tous les livres
    List<Livre> findAll();

    //recupere un livre par son id
    Optional<Livre> findById(Long id);

    //sauvegarder un livre
    Livre save(Livre livre);

}

