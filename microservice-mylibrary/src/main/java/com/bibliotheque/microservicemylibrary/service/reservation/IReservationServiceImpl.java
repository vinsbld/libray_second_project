package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.model.StateEnum;
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

}
