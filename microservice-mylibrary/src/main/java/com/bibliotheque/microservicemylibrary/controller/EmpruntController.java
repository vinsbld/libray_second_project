package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.dto.EmpruntDTO;
import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import com.bibliotheque.microservicemylibrary.service.livre.ILivreService;
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

    @Autowired
    private ILivreService iLivreService;


    @RequestMapping(value = "/listeDesEmprunts/{id}", method = RequestMethod.GET)
    public List<EmpruntDTO> afficherLaListeDesEmpruntsParUtilisateur(@PathVariable("id") Long id){
        List<Emprunt> emprunts = iEmpruntService.findAllByIdUtilisateur(id);
        List<EmpruntDTO> empruntDTOS = new ArrayList<>();

        for (Emprunt e : emprunts) {
            EmpruntDTO eDTO = new EmpruntDTO();
            eDTO.setEmprunt(e);
            Optional<Copie> c = iCopieService.findById(e.getCopie().getId());
            eDTO.setCopie(c.get());
            Optional<Livre> l = iLivreService.findById(e.getCopie().getLivre().getId());
            eDTO.setLivre(l.get());
            empruntDTOS.add(eDTO);
        }

        logger.info("demande la liste des emprunts pour un utilisateur");
        return empruntDTOS;
    }

    @RequestMapping(value = "/emprunt/{id}")
    public Optional<Emprunt> afficherUnEmprunt(@PathVariable("id")Long id){
        Optional<Emprunt>emprunt = iEmpruntService.findById(id);
        logger.info("detail d'un emprunt demandée");
        return emprunt;

    }

    @RequestMapping(value = "/emprunter/{id}", method = RequestMethod.POST)
    public void demandeEmprunt(@PathVariable Long id, @RequestParam Long idUtilisateur){
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Copie copie = iCopieService.findById(id).get();
        copie.setDisponible(false);
        iCopieService.save(copie);
        Emprunt emprunt = new Emprunt();
        emprunt.setCopie(copie);
        emprunt.setDateDeDebutEmprunt(date);
        emprunt.setDateDeFinEmprunt(iEmpruntService.add4Weeks(date));
        emprunt.setProlongerEmprunt(false);
        emprunt.setIdUtilisateur(idUtilisateur);
        iEmpruntService.save(emprunt);
        logger.info("demande de emprunt pour une copie d'un livre");
    }


    @RequestMapping(value = "/prolonger/{id}", method = RequestMethod.POST)
    public void prolongerEmprunt(@PathVariable Long id,@RequestParam Long idUtilisateur){

        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Emprunt emprunt = iEmpruntService.findById(id).get();
        emprunt.setIdUtilisateur(idUtilisateur);
        emprunt.setProlongerEmprunt(true);
        emprunt.setDateDeFinEmprunt(iEmpruntService.add4Weeks(emprunt.getDateDeFinEmprunt()));
        logger.info("demande de prolongation d'un prêt");
        iEmpruntService.save(emprunt);
    }

    @RequestMapping(value = "/retour/{id}", method = RequestMethod.POST)
    public void retournerEmprunt(@PathVariable Long id,@RequestParam Long idUtilisateur){

        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Emprunt emprunt = iEmpruntService.findById(id).get();
        emprunt.setIdUtilisateur(idUtilisateur);
        emprunt.setDateRetour(date);
        emprunt.setRendu(true);
        iEmpruntService.save(emprunt);

    }





}
