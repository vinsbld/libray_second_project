package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.dto.EmpruntDTO;
import com.bibliotheque.microservicemylibrary.model.*;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class EmpruntController {

    @Autowired
    private IEmpruntService iEmpruntService;


    @RequestMapping(value = "/listeDesEmprunts/{id}", method = RequestMethod.GET)
    public List<EmpruntDTO> afficherLaListeDesEmpruntsParUtilisateur(@PathVariable("id") Long id){
        return iEmpruntService.afficherLaListeDesEmpruntsParUtilisateur(id);
    }

    @RequestMapping(value = "/emprunt/{id}")
    public Optional<Emprunt> afficherUnEmprunt(@PathVariable("id")Long id){
        return iEmpruntService.afficherUnEmprunt(id);
    }

    @RequestMapping(value = "/emprunter/{id}", method = RequestMethod.POST)
    public void demandeEmprunt(@PathVariable Long id, @RequestParam Long idUtilisateur){
        iEmpruntService.emprunter(id, idUtilisateur);
    }


    @RequestMapping(value = "/prolonger/{id}", method = RequestMethod.POST)
    public void prolongerEmprunt(@PathVariable Long id,@RequestParam Long idUtilisateur){
        iEmpruntService.prolongerEmprunt(id, idUtilisateur);
    }

    @RequestMapping(value = "/retour/{id}", method = RequestMethod.POST)
    public void retournerEmprunt(@PathVariable Long id,@RequestParam Long idUtilisateur){
        iEmpruntService.retournerEmprunt(id, idUtilisateur);
    }


}