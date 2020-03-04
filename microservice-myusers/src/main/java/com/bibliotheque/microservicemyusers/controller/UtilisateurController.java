package com.bibliotheque.microservicemyusers.controller;

import com.bibliotheque.microservicemyusers.dao.UtilisateurDao;
import com.bibliotheque.microservicemyusers.model.Utilisateur;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Gestion des informations Utilisateurs")
@RestController
public class UtilisateurController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UtilisateurDao utilisateurDao;

    @RequestMapping(name = "/Profil/{pseudo}", method = RequestMethod.GET)
    public Utilisateur findByPseudo(@PathVariable String pseudo){
        Utilisateur utilisateur = utilisateurDao.findByPseudo(pseudo);
        return utilisateur;
    }

}
