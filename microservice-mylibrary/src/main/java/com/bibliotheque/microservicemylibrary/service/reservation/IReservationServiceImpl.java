package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.model.Reservation;
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
    public List<Reservation> findAllByIdUtilisateur(Long id) {
        return iReservationDao.findAllByIdUtilisateur(id);
    }


    /**
     * permet d'afficher la liste de toutes les reservations pour une copie
     * @param id identifiant de la copie
     * @return la liste de toutes les reservations faite pour une copie
     */
    @Override
    public List<Reservation> findAllByCopie_IdOrderByDateDeReservationAsc(Long id) {
        return iReservationDao.findAllByCopie_IdOrderByDateDeReservationAsc(id);
    }


}
