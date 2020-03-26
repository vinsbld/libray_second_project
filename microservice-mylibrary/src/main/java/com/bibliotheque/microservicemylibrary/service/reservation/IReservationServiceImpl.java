package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IReservationServiceImpl implements IReservationService {

    @Autowired
    private IReservationDao iReservationDao;

    /**
     * Permet d'ajouter 4 semaines à une date
     * @param date à laquelle on ajoute les quatres semaines
     * @return la date incrementée
     */
    @Override
    public Date add4Weeks(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_MONTH, 4);
    return calendar.getTime();}

    /**
     * Permet d'afficher la liste des réservations pour un utilisateur
     * @param id identifiant l'utilisateur
     * @return la liste de toutes les réservations faites par l'utilisateur
     */
    @Override
    public List<Reservation> findAllByIdUtilisateur(Long id) {
        return iReservationDao.findAllByIdUtilisateur(id);
    }

    /**
     * Permet de trouver une résercvation
     * @param id identifiant de la réservation
     * @return la réservation
     */
    @Override
    public Optional<Reservation> findById(Long id){
        return iReservationDao.findById(id);
    }

    /**
     * Permet de sauvegarder une réservation
     * @param reservation Objet à sauvegarder
     */
    @Override
    public void save(Reservation reservation){
        iReservationDao.save(reservation);
    }

    /**
     * Permet de trouver les reservations à relancer
     * @param dateNow date du jour
     * @return la liste des réservations dont le retour du livre n'a pas été enregistré,
     * et dont la date de fin du prêt est avant la date du jour
     */
    @Override
    public List<Reservation> relance(Date dateNow){
       return iReservationDao.findAllByDateRetourIsNullAndDateDeFinDuPretBefore(dateNow);
    }

}

