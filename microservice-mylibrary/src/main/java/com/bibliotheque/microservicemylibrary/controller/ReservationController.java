package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import com.bibliotheque.microservicemylibrary.service.livre.ILivreService;
import com.bibliotheque.microservicemylibrary.service.reservation.IReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ReservationController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IEmpruntService iEmpruntService;

    @Autowired
    private IReservationService iReservationService;

    @Autowired
    private ICopieService iCopieService;

    @Autowired
    private ILivreService iLivreService;

    @RequestMapping(value = "/reserver/{id}", method = RequestMethod.POST)
    public void demandeDeReservation(@PathVariable Long id, @RequestParam Long idUtilisateur){
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Copie copie = iCopieService.findById(id).get();
        Reservation reservation = new Reservation();
        reservation.setCopie(copie);
        reservation.setDateDeReservation(date);
        reservation.setIdUtilisateur(idUtilisateur);
        iReservationService.save(reservation);
        logger.info("demande de réservation pour une copie d'un livre");
    }

    @RequestMapping(value = "/listeDesReservations/{id}", method = RequestMethod.GET)
    public List<ReservationDTO> afficherlesReservationsParUtilisateur(@PathVariable("id") Long id){
        List<Reservation> reservations = iReservationService.findAllByIdUtilisateur(id);
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation r : reservations) {
            ReservationDTO rd = new ReservationDTO();
            rd.setReservation(r);
            Emprunt e = iEmpruntService.findByCopie_Id(r.getCopie().getId());
            rd.setEmprunt(e);
            Optional<Copie> c = iCopieService.findById(r.getCopie().getId());
            rd.setCopie(c);
            reservationDTOS.add(rd);
        }

        logger.info("demande la liste des réservations pour un utilisateur");
        return reservationDTOS;
    }

}
