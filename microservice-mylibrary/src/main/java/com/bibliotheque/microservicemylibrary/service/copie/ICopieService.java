package com.bibliotheque.microservicemylibrary.service.copie;

import com.bibliotheque.microservicemylibrary.model.Copie;

import java.util.List;
import java.util.Optional;

public interface ICopieService {

    List<Copie> findAllByLivreId(Long id);

    Optional<Copie> findById(Long id);

    Copie save(Copie copie);

    List<Copie> getCopieLivresDisponibles(Long idLivre);
}
