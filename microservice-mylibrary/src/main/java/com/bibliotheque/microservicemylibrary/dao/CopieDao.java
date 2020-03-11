package com.bibliotheque.microservicemylibrary.dao;

import com.bibliotheque.microservicemylibrary.model.Copie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface
CopieDao extends JpaRepository<Copie, Long> {

    //Récupere la liste des copies d'un livre par son id
    List<Copie> findAllByLivreId(Long id);

    //Récupere une copie par son id
    Optional<Copie> findById(Long id);

}
