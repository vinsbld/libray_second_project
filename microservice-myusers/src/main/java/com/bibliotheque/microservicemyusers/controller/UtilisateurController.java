package com.bibliotheque.microservicemyusers.controller;

import com.bibliotheque.microservicemyusers.dao.UtilisateurDao;
import com.bibliotheque.microservicemyusers.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class UtilisateurController {

    @Autowired
    UtilisateurDao utilisateurDao;

    @RequestMapping(value = "/{pseudo}/connexion", method = RequestMethod.GET)
    public Utilisateur connexionUtilisateur(@PathVariable String pseudo){
        Utilisateur utilisateur = utilisateurDao.findByPseudo(pseudo);
        return utilisateur;}


}
