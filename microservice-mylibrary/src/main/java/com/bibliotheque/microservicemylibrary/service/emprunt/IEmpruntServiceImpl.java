package com.bibliotheque.microservicemylibrary.service.emprunt;

import com.bibliotheque.microservicemylibrary.dao.IEmpruntDao;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IEmpruntServiceImpl implements IEmpruntService {

    @Autowired
    private IEmpruntDao iEmpruntDao;

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
    public List<Emprunt> findAllByIdUtilisateur(Long id) {
        return iEmpruntDao.findAllByIdUtilisateur(id);
    }

    /**
     * Permet de trouver une résercvation
     * @param id identifiant de la réservation
     * @return la réservation
     */
    @Override
    public Optional<Emprunt> findById(Long id){
        return iEmpruntDao.findById(id);
    }

    /**
     * Permet de sauvegarder une réservation
     * @param emprunt Objet à sauvegarder
     */
    @Override
    public void save(Emprunt emprunt){
        iEmpruntDao.save(emprunt);
    }

    /**
     * Permet de trouver les emprunts à relancer
     * @param dateNow date du jour
     * @return la liste des réservations dont le retour du livre n'a pas été enregistré,
     * et dont la date de fin du prêt est avant la date du jour
     */
    @Override
    public List<Emprunt> relance(Date dateNow){
       return iEmpruntDao.findAllByDateRetourIsNullAndAndDateDeFinEmpruntBefore(dateNow);
    }

    @Override
    public Emprunt findByCopie_Id(Long id) {
        return iEmpruntDao.findByCopie_Id(id);
    }

}

