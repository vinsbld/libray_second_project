package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.dao.CopieDao;
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

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CopieDao copieDao;

    @RequestMapping(value = "/copies/{id}")
    public List<Copie> afficherLesCopiesDunLivre(@PathVariable("id")Long id){
        List<Copie>copieList = copieDao.findAllByLivreId(id);
        return copieList;
    }

    @RequestMapping(value = "/copie/{id}")
    public Optional<Copie> afficherUneCopie(@PathVariable("id")Long id){
        Optional<Copie> copie = copieDao.findById(id);
        return copie;
    }


}
