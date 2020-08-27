package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.dto.EmpruntDTO;
import com.bibliotheque.microservicemylibrary.model.*;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class EmpruntController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IEmpruntService iEmpruntService;

    @Autowired
    private ICopieService iCopieService;


    @RequestMapping(value = "/listeDesEmprunts/{id}", method = RequestMethod.GET)
    public List<EmpruntDTO> afficherLaListeDesEmpruntsParUtilisateur(@PathVariable("id") Long id){
        return iEmpruntService.afficherLaListeDesEmpruntsParUtilisateur(id);
    }

    @RequestMapping(value = "/emprunt/{id}")
    public Optional<Emprunt> afficherUnEmprunt(@PathVariable("id")Long id){
        Optional<Emprunt>emprunt = iEmpruntService.findById(id);
        logger.info("detail d'un emprunt demandée");

        return emprunt;
    }

    @RequestMapping(value = "/emprunter/{id}", method = RequestMethod.POST)
    public void demandeEmprunt(@PathVariable Long id, @RequestParam Long idUtilisateur){

        Copie copie = iCopieService.findById(id).get();
        iEmpruntService.emprunter(copie.getId(), idUtilisateur);
    }


    @RequestMapping(value = "/prolonger/{id}", method = RequestMethod.POST)
    public void prolongerEmprunt(@PathVariable Long id,@RequestParam Long idUtilisateur){

        Emprunt emprunt = iEmpruntService.findById(id).get();
        iEmpruntService.prolongerEmprunt(emprunt.getId(), idUtilisateur);
        logger.info("demande de prolongation d'un prêt");
    }

    @RequestMapping(value = "/retour/{id}", method = RequestMethod.POST)
    public void retournerEmprunt(@PathVariable Long id,@RequestParam Long idUtilisateur){

        Emprunt emprunt = iEmpruntService.findById(id).get();
        iEmpruntService.retournerEmprunt(emprunt.getId(), idUtilisateur);
        logger.info("retour du prêt : " + emprunt.getId() +" de la copie : "+emprunt.getCopie().getId()+" du livre : "+emprunt.getCopie().getLivre().getTitre());
    }


}