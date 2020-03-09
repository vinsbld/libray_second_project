package com.bibliotheque.microservicemylibrary.dao;

import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CopieDao extends JpaRepository<Copie, Long> {

    //liste des copies d'un livre
    @Query("select cp from Copie cp where cp.livre.id = :x")
    public List<Copie> findByLivre(@Param("x") Long id);
}
