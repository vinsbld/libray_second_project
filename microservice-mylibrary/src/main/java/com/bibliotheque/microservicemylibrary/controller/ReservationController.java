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
    private ICopieService copieService;


    @RequestMapping(value = "/listeDesReservations/{id}", method = RequestMethod.GET)
    public List<Reservation> afficherLaListeDesReservationsParUtilisateur(@PathVariable("id") Long id){
        List<Reservation> reservations = iReservationService.findAllByIdUtilisateur(id);
        return reservations;
    }

    @RequestMapping(value = "/reserver", method = RequestMethod.POST)
    public void demandeDeReservation(@RequestParam Long id, @RequestParam Long idUtilisateur){
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Copie copie = copieService.findById(id).get();
        copie.setDisponible(false);
        Reservation reservation = new Reservation();
        reservation.setCopie(copie);
        reservation.setDateDeDebutPret(date);
        reservation.setDateDeFinDuPret(iReservationService.add4Weeks(date));
        reservation.setProlongerPret(false);
        reservation.setIdUtilisateur(idUtilisateur);
        iReservationService.addReservation(reservation);
    }


    @RequestMapping(value = "/prolonger/{id}", method = RequestMethod.POST)
    public void prolongerPret(Long id, Reservation reservation){
        iReservationService.prolongerPret(id, reservation);
    }

}
