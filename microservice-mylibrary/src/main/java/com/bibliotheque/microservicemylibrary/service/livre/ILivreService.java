package com.bibliotheque.microservicemylibrary.service.livre;

import com.bibliotheque.microservicemylibrary.model.Livre;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ILivreService {

    List<Livre>findAll();

    Optional<Livre> findById(Long id);

    Livre save(Livre livre);

    List<Livre> chercherParTitre(@Param("x") String motCle);
}
