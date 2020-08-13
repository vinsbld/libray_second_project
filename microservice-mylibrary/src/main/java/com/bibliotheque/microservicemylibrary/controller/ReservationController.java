package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.dto.ReservationDTO;
import com.bibliotheque.microservicemylibrary.exeptions.CannotAddBookingException;
import com.bibliotheque.microservicemylibrary.model.*;
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
    private ILivreService iLivreService;

    @Autowired
    private ICopieService iCopieService;


    @RequestMapping(value = "/reserver/{id}", method = RequestMethod.POST)
    public void demandeDeReservation(@PathVariable("id") Long id, @RequestParam Long idUtilisateur){

        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Reservation reservation = new Reservation();
        reservation.setLivre(iLivreService.findById(id).get());
        reservation.setDateDeReservation(date);
        reservation.setIdUtilisateur(idUtilisateur);
        reservation.setStateEnums(StateEnum.enCours);

        List<Reservation> reservations = iReservationService.findByLivreAndStateEnumsOrderByDateDeReservationAsc(reservation.getLivre(), StateEnum.enCours);
        for (int i = 0; i <= reservations.size(); i++){
            reservation.setPosition(i + 1);
        }


        /*
        la liste ne peut comporter qu'un maximum de personnes correspondant
        à 2x le nombre d'exemplaires de l'ouvrage.
        */
        Integer reservationMax = (reservation.getLivre().getNbCopies())*2;

        //verification si l'utilisateur n'a pas déjà une réservation en cours pour cet ouvrage
        List<Reservation> reservationList = iReservationService.findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(reservation.getLivre().getId(), StateEnum.enCours);
        for (Reservation r : reservationList) {
            if (r.getIdUtilisateur().equals(reservation.getIdUtilisateur())){
                throw new CannotAddBookingException("réservation impossible, vous avez déjà une réservation en cours pour cet ouvrage");
            }
        }

        //verification si l'utilisateur n'a pas déjà un emprunt en cours pour cet ouvrage
        List<Emprunt> empruntList = iEmpruntService.findAllByIdUtilisateurAndDateRetourIsNull(idUtilisateur);
        for (Emprunt e : empruntList) {
            if (e.getCopie().getLivre().getId().equals(reservation.getLivre().getId())){
                throw new CannotAddBookingException("réservation impossible, vous avez déjà un emprunt en cours pour cet ouvrage");
            }
        }

        //verification que la liste n'est pas complète
        if (reservationList.size() >= reservationMax){
            throw new CannotAddBookingException("réservation impossible, la liste des réservations pour cet ouvrage est compléte");
        }

        iReservationService.save(reservation);
        logger.info("demande de réservation pour un livre");
    }

    @RequestMapping(value = "/annuler/reserver/{id}", method = RequestMethod.POST)
    public void annulerReservation(@PathVariable("id") Long id, @RequestParam Long idUtilisateur){
        Optional<Reservation> reservation = iReservationService.findById(id);

        Reservation r = reservation.get();
        r.setStateEnums(StateEnum.annuler);
        r.setPosition(null);

        logger.info("l'utilisateur : "+ idUtilisateur + " a annuler sa reservation pour le livre : "+r.getLivre().getTitre());
        iReservationService.save(r);

    }

    @RequestMapping(value = "/listeDesReservations/{id}", method = RequestMethod.GET)
    public List<ReservationDTO> afficherlesReservationsParUtilisateur(@PathVariable("id") Long id){

        List<Reservation> reservations = iReservationService.findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(id, StateEnum.enCours);
        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation r : reservations) {
            ReservationDTO rd = new ReservationDTO();
            rd.setReservation(r);
            Emprunt e = iEmpruntService.findByCopie_Id(r.getLivre().getId());
            rd.setEmprunt(e);
            Optional<Livre> l = iLivreService.findById(r.getLivre().getId());
            rd.setLivre(l.get());

            //recuperer la date de retour la plus proche
            List<Date> dates = new ArrayList<>();
            List<Copie> copies = iCopieService.findAllByLivreId(l.get().getId());
            for (Copie c : copies) {
                List<Emprunt> emprunts = iEmpruntService.findAllByCopie_IdAndDateRetourIsNull(c.getId());
                if (emprunts.size() > 0){
                    Emprunt emprunt = emprunts.get(0);
                    dates.add(emprunt.getDateDeFinEmprunt());
                }
            }
            Collections.sort(dates);
            if (dates.size() > 0){
                Date dateLaPlusProche = dates.get(0);
                l.get().setDateRetourLaPlusProche(dateLaPlusProche);
            }

            //afficher la position de l'utilisateur dans la liste de reservation
            List<Reservation> rc = iReservationService.findAllByLivreAndStateEnumsOrderByDateDeReservationAsc(l.get(), StateEnum.enCours);
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

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public List<Reservation> listDeToutesLesReservations(){
        List<Reservation> reservations = iReservationService.findByEmailEnvoyerAndStateEnums(true, StateEnum.enCours);
        return reservations;
    }

}
