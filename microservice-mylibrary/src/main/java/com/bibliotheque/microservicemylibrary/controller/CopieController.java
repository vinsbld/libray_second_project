package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
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

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICopieService iCopieService;

    @RequestMapping(value = "/copies/{id}")
    public List<Copie> afficherLesCopiesDunLivre(@PathVariable("id")Long id){
        List<Copie>copieList = iCopieService.findAllByLivreId(id);
        logger.info("demande d'une liste de copies d'un livre");
        return copieList;
    }

    @RequestMapping(value = "/copie/{id}")
    public Optional<Copie> afficherUneCopie(@PathVariable("id")Long id){
        Optional<Copie> copie = iCopieService.findById(id);
        logger.info("demande d'une copie d'un livre");
        return copie;
    }

    @RequestMapping(value = "/copies/dispos/{id}")
    public List<Copie> afficherLesCopiesDisponibles(@PathVariable("id") Long id){
        List<Copie> copiesDisponibles = iCopieService.getCopieLivresDisponibles(id);
        logger.info("demande des copies disponibles pour un livre");
        return copiesDisponibles;
    }


}
