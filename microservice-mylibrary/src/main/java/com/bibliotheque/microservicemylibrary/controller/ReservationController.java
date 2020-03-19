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
import java.util.Optional;


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

    @RequestMapping(value = "/reservation/{id}")
    public Optional<Reservation> affivherUneReservation(@PathVariable("id")Long id){
        Optional<Reservation>reservation = iReservationService.findById(id);
        return reservation;

    }

    @RequestMapping(value = "/reserver/{id}", method = RequestMethod.POST)
    public void demandeDeReservation(@PathVariable Long id, @RequestParam Long idUtilisateur){
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Copie copie = copieService.findById(id).get();
        copie.setDisponible(false);
        copieService.save(copie);
        Reservation reservation = new Reservation();
        reservation.setCopie(copie);
        reservation.setDateDeDebutPret(date);
        reservation.setDateDeFinDuPret(iReservationService.add4Weeks(date));
        reservation.setProlongerPret(false);
        reservation.setIdUtilisateur(idUtilisateur);
        iReservationService.save(reservation);
    }


    @RequestMapping(value = "/prolonger/{id}", method = RequestMethod.POST)
    public void prolongerPret(@PathVariable Long id,@RequestParam Long idUtilisateur){

        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Reservation reservation = iReservationService.findById(id).get();
        reservation.setIdUtilisateur(idUtilisateur);
        reservation.setProlongerPret(true);
        reservation.setDateDeFinDuPret(iReservationService.add4Weeks(reservation.getDateDeFinDuPret()));

        iReservationService.save(reservation);
    }

}
