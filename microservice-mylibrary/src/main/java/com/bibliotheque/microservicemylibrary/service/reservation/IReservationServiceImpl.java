package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.dto.ReservationDTO;
import com.bibliotheque.microservicemylibrary.exeptions.CannotAddBookingException;
import com.bibliotheque.microservicemylibrary.model.*;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import com.bibliotheque.microservicemylibrary.service.livre.ILivreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IReservationServiceImpl implements IReservationService{

    @Autowired
    private IReservationDao iReservationDao;

    @Autowired
    private ILivreService iLivreService;

    @Autowired
    private IEmpruntService iEmpruntService;

    @Autowired
    private ICopieService iCopieService;


    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * permet de sauvegarder une réservation
     * @param reservation Objet à sauvegarder
     */
    @Override
    public void save(Reservation reservation) {
        iReservationDao.save(reservation);
    }

    /**
     * permet de supprimer les reservations
     * @param id identifiant de la reservation
     */
    @Override
    public void deleteById(Long id) {
        iReservationDao.deleteById(id);
    }

    /**
     * trouver une reservation
     * @param id idendifiant de la reservation
     * @return la reservation
     */
    @Override
    public Optional<Reservation> findById(Long id) {
        return iReservationDao.findById(id);
    }


    /**
     * Permet d'afficher la liste des reservations pour un utilisateur
     * @param id identifiant l'utilisateur
     * @param stateEnum état de la reservation
     * @return la liste de toutes les réservations faites par l'utilisateur
     */
    @Override
    public List<Reservation> findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(Long id, StateEnum stateEnum) {
        return iReservationDao.findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(id, stateEnum);
    }


    /**
     * permet d'afficher la liste de toutes les reservations pour un livre
     * @param livre
     * @param stateEnum
     * @return permet d'afficher la liste de toutes les reservations pour un livre
     */
    @Override
    public List<Reservation> findAllByLivreAndStateEnumsOrderByDateDeReservationAsc(Livre livre, StateEnum stateEnum) {
        return iReservationDao.findAllByLivreAndStateEnumsOrderByDateDeReservationAsc(livre, stateEnum);
    }


    /**
     * permet d'afficher une reservation pour un livre
     * @param livre
     * @param stateEnum
     * @return la reservation pour un livre
     */
    @Override
    public List<Reservation> findByLivreAndStateEnumsOrderByDateDeReservationAsc(Livre livre, StateEnum stateEnum) {
        return iReservationDao.findByLivreAndStateEnumsOrderByDateDeReservationAsc(livre, stateEnum);
    }



    /**
     * permet de d'afficher les reservations dont le mail a été envoyé ou non seulon l'état de la reservation
     * @param emailEnvoyer
     * @param stateEnum
     * @return les reservations
     */
    @Override
    public List<Reservation> findByEmailEnvoyerAndStateEnums(Boolean emailEnvoyer, StateEnum stateEnum) {
        return iReservationDao.findByEmailEnvoyerAndStateEnums(emailEnvoyer, stateEnum);
    }

    /**
     * trouver toutes les reservations
     * @return la liste de toutes les reservations
     */
    @Override
    public List<Reservation> findAll() {
        return iReservationDao.findAll();
    }

    /**
     * permet de faire une réservation
     * @param id idendifiant du livre
     * @param idUtilisateur
     */
    @Override
    public void reserver(Long id, Long idUtilisateur) {

        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Reservation reservation = new Reservation();
        reservation.setLivre(iLivreService.findById(id).get());
        reservation.setDateDeReservation(date);
        reservation.setIdUtilisateur(idUtilisateur);
        reservation.setStateEnums(StateEnum.enCours);

        List<Reservation> reservations = iReservationDao.findByLivreAndStateEnumsOrderByDateDeReservationAsc(reservation.getLivre(), StateEnum.enCours);
        for (int i = 0; i <= reservations.size(); i++){
            int position = i + 1;
            reservation.setPosition(position);
        }

        /*
        la liste ne peut comporter qu'un maximum de personnes correspondant
        à 2x le nombre d'exemplaires de l'ouvrage.
        */
        Integer reservationMax = (reservation.getLivre().getNbCopies())*2;

        //verification si l'utilisateur n'a pas déjà une réservation en cours pour cet ouvrage
        List<Reservation> reservationList = iReservationDao.findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(idUtilisateur, StateEnum.enCours);
        for (Reservation r : reservationList) {
            if (r.getIdUtilisateur().equals(reservation.getIdUtilisateur())){
                throw new CannotAddBookingException("cannotBookingException01");
            }
        }

        //verification si l'utilisateur n'a pas déjà un emprunt en cours pour cet ouvrage
        List<Emprunt> empruntList = iEmpruntService.findAllByIdUtilisateurAndDateRetourIsNull(idUtilisateur);
        for (Emprunt e : empruntList) {
            if (e.getCopie().getLivre().getId().equals(reservation.getLivre().getId())){
                throw new CannotAddBookingException("cannotBookingException02");
            }
        }

        //verification que la liste n'est pas complète
        if (reservations.size() >= reservationMax){
            throw new CannotAddBookingException("cannotBookingException03");
        }

        iReservationDao.save(reservation);
        logger.info("demande de réservation pour un livre");
    }

    /**
     * permet d'annuler une réservation
     * @param id identifiant de la réservation
     * @param idUtilisateur
     */
    @Override
    public void annuler(Long id, Long idUtilisateur) {
        Optional<Reservation> reservation = iReservationDao.findById(id);
        Reservation r = reservation.get();
        r.setStateEnums(StateEnum.annuler);
        r.setPosition(null);
        iReservationDao.save(r);
        logger.info("l'utilisateur : "+ idUtilisateur + " a annuler sa reservation pour le livre : "+r.getLivre().getTitre());
    }

    /**
     * permet d'afficher les réservations d'un utilisateur
     * @param id identifiant de l'utilisateur
     * @return la liste des réservations de l'utilisateur
     */
    @Override
    public List<ReservationDTO> afficherlesReservations(Long id) {

        List<Reservation> reservations = iReservationDao.findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(id, StateEnum.enCours);
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
            List<Reservation> rc = iReservationDao.findAllByLivreAndStateEnumsOrderByDateDeReservationAsc(l.get(), StateEnum.enCours);
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

    /**
     * permet d'afficher toutes les réservations
     * @return la liste de toutes les réservations
     */
    @Override
    public List<Reservation> toutesLesReservations() {
        List<Reservation> reservations = iReservationDao.findByEmailEnvoyerAndStateEnums(true, StateEnum.enCours);
        return reservations;
    }


}