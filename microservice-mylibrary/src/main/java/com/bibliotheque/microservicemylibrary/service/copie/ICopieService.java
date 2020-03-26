package com.bibliotheque.microservicemylibrary.service.copie;

import com.bibliotheque.microservicemylibrary.model.Copie;

import java.util.List;
import java.util.Optional;

public interface ICopieService {

    //trouve les dopie d'un livre par son id
    List<Copie> findAllByLivreId(Long id);

    //trouve une copie par son id
    Optional<Copie> findById(Long id);

    //enregistre une copie
    Copie save(Copie copie);

    //trouve les copies disponibles d'un livre
    List<Copie> getCopieLivresDisponibles(Long idLivre);
}
