package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.configurations.ApplicationPropertiesConfiguration;
import com.bibliotheque.microservicemylibrary.exeptions.LivresNotFoundExeption;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.service.LivreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class LivreController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationPropertiesConfiguration appProperties;

    @Autowired
    private LivreService livreService;

    @RequestMapping(value = "/livres")
    public List<Livre> ListeDeLivres(){
        List<Livre>livres = livreService.findAll();
        if (livres.isEmpty()) throw new LivresNotFoundExeption("Il n'y a pas de livres");
        logger.info("Récupération de la liste des produits");
        return livres;
    }

    @RequestMapping(value = "/livre/{id}")
    public Optional<Livre> afficherUnLivre(@PathVariable Long id) {
        Optional<Livre> livre = livreService.findById(id);
        logger.info("Le détail d'un livre est demandé");
        return livre;
    }


}
