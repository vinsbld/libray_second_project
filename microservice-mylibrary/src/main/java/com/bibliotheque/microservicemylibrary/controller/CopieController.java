package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.dao.CopieDao;
import com.bibliotheque.microservicemylibrary.dao.LivreDao;
import com.bibliotheque.microservicemylibrary.model.Copie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CopieController {

    @Autowired
    private CopieDao copieDao;

    @Autowired
    private LivreDao livreDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/copie/{id}")
    public Optional<Copie> afficheUneCopie(@PathVariable Long id){
        Optional<Copie> copie = copieDao.findById(id);
        logger.info("L'affichage d'une copie d'un livre est demandé");
        return copie;
    }

    @RequestMapping(value = "/copies/{id}")
    public List<Copie> afficherLesCopiesLivre(@PathVariable Long id){
       List<Copie> copie = copieDao.findByLivre(id);
        return copie;
    }

}
