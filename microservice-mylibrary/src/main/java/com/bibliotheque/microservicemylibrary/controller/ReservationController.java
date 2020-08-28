package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.dto.ReservationDTO;
import com.bibliotheque.microservicemylibrary.model.*;
import com.bibliotheque.microservicemylibrary.service.reservation.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ReservationController {

    @Autowired
    private IReservationService iReservationService;


    @RequestMapping(value = "/reserver/{id}", method = RequestMethod.POST)
    public void demandeDeReservation(@PathVariable("id") Long id, @RequestParam Long idUtilisateur){
        iReservationService.reserver(id, idUtilisateur);
    }

    @RequestMapping(value = "/annuler/reserver/{id}", method = RequestMethod.POST)
    public void annulerReservation(@PathVariable("id") Long id, @RequestParam Long idUtilisateur){
        iReservationService.annuler(id, idUtilisateur);
    }

    @RequestMapping(value = "/listeDesReservations/{id}", method = RequestMethod.GET)
    public List<ReservationDTO> afficherlesReservationsParUtilisateur(@PathVariable("id") Long id) {
      return iReservationService.afficherlesReservations(id);
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public List<Reservation> listDeToutesLesReservations(){
        return iReservationService.toutesLesReservations();
    }

}