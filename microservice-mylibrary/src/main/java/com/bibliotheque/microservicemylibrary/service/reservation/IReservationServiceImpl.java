package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
