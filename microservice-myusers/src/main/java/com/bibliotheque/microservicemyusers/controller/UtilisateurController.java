package com.bibliotheque.microservicemyusers.controller;

import com.bibliotheque.microservicemyusers.dao.UtilisateurDao;
import com.bibliotheque.microservicemyusers.model.Utilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UtilisateurController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UtilisateurDao utilisateurDao;

    @GetMapping(name = "/{pseudo}/connexion")
    public Utilisateur connexionUtilisateur(@PathVariable String pseudo){
        Utilisateur utilisateur = utilisateurDao.findByPseudo(pseudo);
        return utilisateur;
    }


}
