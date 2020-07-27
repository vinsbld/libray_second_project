package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
public class EmpruntController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IEmpruntService iEmpruntService;

    @Autowired
    private ICopieService copieService;


    @RequestMapping(value = "/listeDesReservations/{id}", method = RequestMethod.GET)
    public List<Emprunt> afficherLaListeDesReservationsParUtilisateur(@PathVariable("id") Long id){
        List<Emprunt> emprunts = iEmpruntService.findAllByIdUtilisateur(id);
        logger.info("demande la liste des reservation pour un utilisateur");
        return emprunts;
    }

    @RequestMapping(value = "/reservation/{id}")
    public Optional<Emprunt> affivherUneReservation(@PathVariable("id")Long id){
        Optional<Emprunt>reservation = iEmpruntService.findById(id);
        logger.info("detail d'une reservation demandée");
        return reservation;

    }

    @RequestMapping(value = "/reserver/{id}", method = RequestMethod.POST)
    public void demandeDeReservation(@PathVariable Long id, @RequestParam Long idUtilisateur){
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Copie copie = copieService.findById(id).get();
        copie.setDisponible(false);
        copieService.save(copie);
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
    public void prolongerPret(@PathVariable Long id,@RequestParam Long idUtilisateur){

        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Emprunt emprunt = iEmpruntService.findById(id).get();
        emprunt.setIdUtilisateur(idUtilisateur);
        emprunt.setProlongerEmprunt(true);
        emprunt.setDateDeFinEmprunt(iEmpruntService.add4Weeks(emprunt.getDateDeFinEmprunt()));
        logger.info("demande de prolongation d'un prêt");
        iEmpruntService.save(emprunt);
    }

    @RequestMapping(value = "/retour/{id}", method = RequestMethod.POST)
    public void retournerUnPret(@PathVariable Long id,@RequestParam Long idUtilisateur){

        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Emprunt emprunt = iEmpruntService.findById(id).get();
        emprunt.setIdUtilisateur(idUtilisateur);
        emprunt.setDateRetour(date);
        emprunt.setRendu(true);
        iEmpruntService.save(emprunt);

    }



}
