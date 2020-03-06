package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.configurations.ApplicationPropertiesConfiguration;
import com.bibliotheque.microservicemylibrary.dao.LivreDao;
import com.bibliotheque.microservicemylibrary.exeptions.LivresNotFoundExeption;
import com.bibliotheque.microservicemylibrary.model.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class LivreController {

    @Autowired
    private LivreDao livreDao;

    @Autowired
    ApplicationPropertiesConfiguration appProperties;

    @RequestMapping(value = "/livres")
    public List<Livre> ListeDeLivres(){
        List<Livre>livres = livreDao.findAll();
        if (livres.isEmpty()) throw new LivresNotFoundExeption("Il n'y a pas de livres");
        //List<Livre> listeLimitee = livres.subList(0, appProperties.getLimiteDeLivres());
        return livres;
    }

    @RequestMapping(value = "/livre/{id}")
    public Optional<Livre> afficherUnLivre(@PathVariable Long id) {

        Optional<Livre> livre = livreDao.findById(id);

        return livre;
    }

}
