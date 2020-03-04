package com.bibliotheque.microservicemylibrary.dao;

import com.bibliotheque.microservicemylibrary.model.Copie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopieDao extends JpaRepository<Copie, Long> {

}
