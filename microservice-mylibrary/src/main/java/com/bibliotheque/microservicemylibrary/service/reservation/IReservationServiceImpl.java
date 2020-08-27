package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.exeptions.CannotAddBookingException;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.model.StateEnum;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import com.bibliotheque.microservicemylibrary.service.livre.ILivreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IReservationServiceImpl implements IReservationService{

    @Autowired
    private IReservationDao iReservationDao;

    @Autowired
    private ILivreService iLivreService;

    @Autowired
    private IEmpruntService iEmpruntService;

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
            reservation.setPosition(i + 1);
        }

        /*
        la liste ne peut comporter qu'un maximum de personnes correspondant
        à 2x le nombre d'exemplaires de l'ouvrage.
        */
        Integer reservationMax = (reservation.getLivre().getNbCopies())*2;

        //verification si l'utilisateur n'a pas déjà une réservation en cours pour cet ouvrage
        List<Reservation> reservationList = iReservationDao.findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(reservation.getLivre().getId(), StateEnum.enCours);
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
        if (reservationList.size() >= reservationMax){
            throw new CannotAddBookingException("cannotBookingException03");
        }

        iReservationDao.save(reservation);
        logger.info("demande de réservation pour un livre");
    }

}