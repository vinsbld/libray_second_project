package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.dto.CopieDTO;
import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CopieController {

    @Autowired
    private ICopieService iCopieService;


    @RequestMapping(value = "/copies/{id}")
    public List<CopieDTO> afficherLesCopiesDunLivre(@PathVariable("id")Long id){
    return iCopieService.afficherLesCopies(id);
    }


    @RequestMapping(value = "/copie/{id}")
    public CopieDTO afficherUneCopie(@PathVariable("id")Long id){
    return iCopieService.voirCopie(id);
    }

    @RequestMapping(value = "/copies/dispos/{id}")
    public List<Copie> afficherLesCopiesDisponibles(@PathVariable("id") Long id){
    return iCopieService.voirLesCopiesDisponibles(id);
    }

    @RequestMapping(value = "copies/nonDispos/{id}")
    public List<Copie> afficherLesCopiesNonDisponibles(@PathVariable("id")Long id){
    return iCopieService.voirLesCopiesNonDisponibles(id);
    }

}