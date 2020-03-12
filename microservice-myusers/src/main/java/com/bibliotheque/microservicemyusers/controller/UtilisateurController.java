package com.bibliotheque.microservicemyusers.controller;

import com.bibliotheque.microservicemyusers.model.Utilisateur;
import com.bibliotheque.microservicemyusers.service.IUtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class UtilisateurController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUtilisateurService IUtilisateurService;

    @RequestMapping(value = "/{pseudo}/connexion", method = RequestMethod.GET)
    public Utilisateur connexionUtilisateur(@PathVariable String pseudo){
        Utilisateur utilisateur = IUtilisateurService.findByPseudo(pseudo);
        logger.info("Une demande de connexion à été faite");
        return utilisateur;}


}
