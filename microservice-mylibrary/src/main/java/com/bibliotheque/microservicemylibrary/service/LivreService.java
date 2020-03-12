package com.bibliotheque.microservicemylibrary.service;

import com.bibliotheque.microservicemylibrary.model.Livre;

import java.util.List;
import java.util.Optional;

public interface LivreService {

    List<Livre> findAll();

    Optional<Livre> findById(Long id);
}
