package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.model.StateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IReservationServiceImpl implements IReservationService{

    @Autowired
    private IReservationDao iReservationDao;

    /**
     * permet de sauvegarder une réservation
     * @param reservation Objet à sauvegarder
     */
    @Override
    public void save(Reservation reservation) {
        iReservationDao.save(reservation);
    }

    /**
     * Permet d'afficher la liste des reservations pour un utilisateur
     * @param id identifiant l'utilisateur
     * @return la liste de toutes les réservations faites par l'utilisateur
     */
    @Override
    public List<Reservation> findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(Long id, StateEnum stateEnum) {
        return iReservationDao.findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(id, stateEnum);
    }

    /**
     * permet d'afficher la liste de toutes les reservations pour un livre
     * @param id identifiant du livre
     * @param stateEnum
     * @return la liste de toutes les reservations faite pour un livre
     */
    @Override
    public List<Reservation> findAllByLivre_IdAndStateEnumsOrderByDateDeReservationAsc(Long id, StateEnum stateEnum) {
        return iReservationDao.findAllByLivre_IdAndStateEnumsOrderByDateDeReservationAsc(id, stateEnum);
    }


}
