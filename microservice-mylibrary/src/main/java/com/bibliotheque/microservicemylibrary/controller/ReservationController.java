package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.reservation.IReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class ReservationController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IReservationService iReservationService;

    @Autowired
    private ICopieService iCopieService;

    @RequestMapping(value = "/reserver/{id}", method = RequestMethod.POST)
    public void demandeDeReservation(@PathVariable Long id, @RequestParam Long idUtilisateur){
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Copie copie = iCopieService.findById(id).get();
        Reservation reservation = new Reservation();
        reservation.setCopieReservation(copie);
        reservation.setDateDeReservation(date);
        reservation.setIdUtilisateur(idUtilisateur);
        iReservationService.save(reservation);
        logger.info("demande de réservation pour une copie d'un livre");
    }

    @RequestMapping(value = "/listeDesReservations/{id}", method = RequestMethod.GET)
    public List<Reservation> afficherlesReservationsParUtilisateur(@PathVariable("id") Long id){
        List<Reservation> reservations = iReservationService.findAllByIdUtilisateur(id);
        logger.info("demande la liste des réservations pour un utilisateur");
        return reservations;
    }

}
