package com.bibliotheque.microservicemylibrary.service;

import com.bibliotheque.microservicemylibrary.model.Copie;

import java.util.List;
import java.util.Optional;

public interface CopieService {

    List<Copie> findAllByLivreId(Long id);

    Optional<Copie> findById(Long id);
}
