package com.bibliotheque.microservicemylibrary.service.emprunt;

import com.bibliotheque.microservicemylibrary.dao.IEmpruntDao;
import com.bibliotheque.microservicemylibrary.model.Copie;
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
     * Permet d'afficher la liste des emprunts pour un utilisateur
     * @param id identifiant l'utilisateur
     * @return la liste de tous les emprunts faites par l'utilisateur
     */
    @Override
    public List<Emprunt> findAllByIdUtilisateur(Long id) {
        return iEmpruntDao.findAllByIdUtilisateur(id);
    }

    /**
     * Permet de trouver un emprunt
     * @param id identifiant de l'emprunt
     * @return l'emprunt
     */
    @Override
    public Optional<Emprunt> findById(Long id){
        return iEmpruntDao.findById(id);
    }

    /**
     * Permet de sauvegarder un emprunt
     * @param emprunt Objet à sauvegarder
     */
    @Override
    public void save(Emprunt emprunt){
        iEmpruntDao.save(emprunt);
    }

    /**
     * Permet de trouver les emprunts à relancer
     * @param dateNow date du jour
     * @return la liste des emprunts dont le retour du livre n'a pas été enregistré,
     * et dont la date de fin du prêt est avant la date du jour
     */
    @Override
    public List<Emprunt> relance(Date dateNow){
       return iEmpruntDao.findAllByDateRetourIsNullAndAndDateDeFinEmpruntBefore(dateNow);
    }

    /**
     * permet de trouver un Emprunt par copies de livre
     * @param id idetifiant de la copie
     * @return l'emprunt de la copie
     */
    @Override
    public Emprunt findByCopie_Id(Long id) {
        return iEmpruntDao.findByCopie_Id(id);
    }



    /**
     * permet de trouver tous les emprunts par copie de livre
     * @param id identifiant de la copie
     * @return la liste des emprunts de la copie
     */
    @Override
    public List<Emprunt> findAllByCopie_IdAndDateRetourIsNull(Long id) {
        return iEmpruntDao.findAllByCopie_IdAndDateRetourIsNull(id);
    }


}

