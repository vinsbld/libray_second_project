package com.bibliotheque.microservicemylibrary.service.copie;

import com.bibliotheque.microservicemylibrary.dto.CopieDTO;
import com.bibliotheque.microservicemylibrary.model.Copie;
import org.springframework.web.bind.annotation.PathVariable;

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

    //trouve les copies inDisponibles d'un livre
    List<Copie> getCopieLivresIndisponibles(Long id);

    //afficher les copies d'un livre
    List<CopieDTO> afficherLesCopies(Long id);

    //afficher une copie
    CopieDTO voirCopie(Long id);

    //afficher les copies disponibles
    List<Copie> voirLesCopiesDisponibles(Long id);

    //afficher les copies indisponibles
    List<Copie> voirLesCopiesNonDisponibles(Long id);
}