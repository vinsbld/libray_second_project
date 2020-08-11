package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.dto.CopieDTO;
import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class CopieController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICopieService iCopieService;

    @Autowired
    private IEmpruntService iEmpruntService;

    @RequestMapping(value = "/copies/{id}")
    public List<CopieDTO> afficherLesCopiesDunLivre(@PathVariable("id")Long id){
        List<Copie>copieList = iCopieService.findAllByLivreId(id);
        List<CopieDTO> copieDTOs = new ArrayList<>();
        for (Copie c : copieList) {
            CopieDTO cp = new CopieDTO();
            cp.setCopie(c);
            Emprunt r = iEmpruntService.findByCopie_Id(c.getId());
            cp.setEmprunt(r);
            copieDTOs.add(cp);
        }
        logger.info("demande d'une liste de copies d'un livre");
        return copieDTOs;
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

    @RequestMapping(value = "copies/nonDispos/{id}")
    public List<Copie> afficherLesCopiesNonDisponibles(@PathVariable("id")Long id){
        List<Copie> copiesNonDispos = iCopieService.getCopieLivresIndisponibles(id);
        return copiesNonDispos;
    }

}
