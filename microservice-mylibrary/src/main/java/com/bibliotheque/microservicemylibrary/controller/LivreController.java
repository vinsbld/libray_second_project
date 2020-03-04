package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.dao.LivreDao;
import com.bibliotheque.microservicemylibrary.model.Livre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class LivreController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LivreDao livreDao;

    @RequestMapping(value = "/livres")
    public List<Livre> ListeDeLivres(){

        List<Livre>livres = livreDao.findAll();

        return livres;
    }

    @RequestMapping(value = "/livre/{id}")
    public Optional<Livre> afficherUnLivre(@PathVariable Long id) {

        Optional<Livre> livre = livreDao.findById(id);

        return livre;
    }

}
