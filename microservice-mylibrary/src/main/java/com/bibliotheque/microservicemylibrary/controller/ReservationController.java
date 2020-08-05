package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.exeptions.CannotAddBookingException;
import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
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


    @RequestMapping(value = "/reserver/{id}", method = RequestMethod.POST)
    public void demandeDeReservation(@PathVariable Long id, @RequestParam Long idUtilisateur){

        Copie copie = iCopieService.findById(id).get();
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Reservation reservation = new Reservation();
        reservation.setCopie(copie);
        reservation.setDateDeReservation(date);
        reservation.setIdUtilisateur(idUtilisateur);

        /*
        la liste ne peut comporter qu'un maximum de personnes correspondant
        à 2x le nombre d'exemplaires de l'ouvrage.
        */
        Integer reservationMax = (reservation.getCopie().getLivre().getNbCopies())*2;

        //verification si l'utilisateur n'a pas déjà une réservation en cours pour cet ouvrage
        List<Reservation> reservationList = iReservationService.findAllByCopie_IdOrderByDateDeReservationAsc(copie.getId());
        for (Reservation r : reservationList) {
            if (r.getIdUtilisateur().equals(reservation.getIdUtilisateur())){
                throw new CannotAddBookingException("ReservationExeption01");
            }
        }

        //verification si l'utilisateur n'a pas déjà un emprunt en cours pour cet ouvrage
        List<Emprunt> empruntList = iEmpruntService.findAllByIdUtilisateur(idUtilisateur);
        for (Emprunt e : empruntList) {
            if (e.getCopie().getLivre().getId().equals(reservation.getCopie().getLivre().getId())){
                throw new CannotAddBookingException("ReservationException02");
            }
        }

        //verification que la liste n'est pas complète
        if (reservationList.size() >= reservationMax){
            throw new CannotAddBookingException("ReservationException03");
        }

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
            //afficher la position de l'utilisateur dans la liste de reservation
            List<Reservation> rc = iReservationService.findAllByCopie_IdOrderByDateDeReservationAsc(c.get().getId());
            for (int i = 0; i< rc.size(); i ++){
                if (rc.get(i).getIdUtilisateur() == id){
                    rd.setPosition(i + 1);
                }
            }
            reservationDTOS.add(rd);
        }

        logger.info("demande la liste des réservations pour un utilisateur");
        return reservationDTOS;
    }

    @RequestMapping(value = "/liste/{id}", method = RequestMethod.GET)
    public List<Reservation> afficherLesreservationsParCopie(@PathVariable("id") Long id){
        List<Reservation> reservations = iReservationService.findAllByCopie_IdOrderByDateDeReservationAsc(id);
        return reservations;
    }

}
