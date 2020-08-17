package com.bibliotheque.microservicemylibrary.dao;

import com.bibliotheque.microservicemylibrary.model.Copie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface
ICopieDao extends JpaRepository<Copie, Long> {

    //Récupere la liste des copies d'un livre par son id
    List<Copie> findAllByLivreId(Long id);

    //Récupere une copie par son id
    Optional<Copie> findById(Long id);

    //sauvegarder une copie
    Copie save(Copie copie);

    //trouve les copies d'un livre qui sont disponibles
    @Query("SELECT c FROM Copie c WHERE  c.disponible = true and c.livre.id = :id ")
    List<Copie> getCopieLivresDisponibles(Long id);

    //trouve les copies d'un livre non disponibles
    @Query("SELECT c FROM Copie c WHERE c.disponible = false and c.livre.id = :id")
    List<Copie> getCopieLivresIndisponibles(Long id);


}
