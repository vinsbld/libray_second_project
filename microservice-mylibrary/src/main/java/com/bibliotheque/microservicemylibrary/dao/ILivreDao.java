package com.bibliotheque.microservicemylibrary.dao;

import com.bibliotheque.microservicemylibrary.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    //recherche par titre
    @Query("select t from Livre t where lower(t.titre) like lower(concat('%',:x,'%')) order by t.id")
    List<Livre> chercherParTitre(@Param("x")String motCle);

    //recherche par nom auteur
    @Query("select n from Livre n where lower(n.nomAuteur) like lower(concat('%',:x,'%')) order by n.id")
    List<Livre> chercherParNomAuteur(@Param("x")String motcle);


}

