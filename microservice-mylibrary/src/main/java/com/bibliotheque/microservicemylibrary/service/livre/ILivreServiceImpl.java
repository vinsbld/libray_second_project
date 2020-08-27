package com.bibliotheque.microservicemylibrary.service.livre;

import com.bibliotheque.microservicemylibrary.dao.ILivreDao;
import com.bibliotheque.microservicemylibrary.exeptions.LivresNotFoundException;
import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ILivreServiceImpl implements ILivreService {

    @Autowired
    ILivreDao mLivreDao;

    @Autowired
    ICopieService iCopieService;

    @Autowired
    IEmpruntService iEmpruntService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Permet la recherche de tous les livres
     * @return la liste de tous les livres
     */
    @Override
    public List<Livre> findAll(){return  mLivreDao.findAll();
    }


    /**
     * Permet la recherche d'un livre
     * @param id du livre
     * @return le livre
     */
    @Override
    public Optional<Livre> findById(Long id) {
        return mLivreDao.findById(id);
    }

    /**
     * Permet d'enregistrer un livre
     * @param livre Objet à enregistrer
     * @return Objet livre
     */
    @Override
    public Livre save(Livre livre){
        return mLivreDao.save(livre);
    }

    /**
     * Permet de rechercher un livre par son titre
     * @param motCle saisie de la recherche
     * @return le resultat de la recherche
     */
    @Override
    public List<Livre> chercherParTitre(@Param("x")String motCle){
        return mLivreDao.chercherParTitre(motCle);
    }

    /**
     * permet de trouver un livre par l'id d'une copie
     * @param id identifiant de la copie
     * @return le livre
     */
    @Override
    public Optional<Livre> findByCopiesId(Long id) {
        return mLivreDao.findByCopiesId(id);
    }

    /**
     * Fonction pour trouver la date de retour la plus proche pour un livre
     * @param livre
     */
    public void dateDeRetourLaplusProche(Livre livre) {

        List<Date> dates = new ArrayList<>();
        List<Copie> copies = iCopieService.findAllByLivreId(livre.getId());
        for (Copie c : copies) {
            List<Emprunt> emprunts = iEmpruntService.findAllByCopie_IdAndDateRetourIsNull(c.getId());
            if (emprunts.size() > 0) {
                Emprunt emprunt = emprunts.get(0);
                dates.add(emprunt.getDateDeFinEmprunt());
            }
        }
        Collections.sort(dates);
        if (dates.size() > 0) {
            Date dateLaPlusProche = dates.get(0);
            livre.setDateRetourLaPlusProche(dateLaPlusProche);
        }
    }

    /**
     * permet d'afficher la liste de tous les livres
     * @return la liste des livres
     */
    @Override
    public List<Livre> livres() {
        List<Livre> livres = mLivreDao.findAll();
        if (livres.isEmpty()) throw new LivresNotFoundException("Il n'y a pas de livres");
        logger.info("Récupération de la liste des produits");
        return livres;
    }

    /**
     * permet d'afficher le détail d'un livre
     * @param id identifiant du livre
     * @return le livre
     */
    @Override
    public Optional<Livre> livre(Long id) {
        Optional<Livre> livre = mLivreDao.findById(id);
        dateDeRetourLaplusProche(livre.get());
        logger.info("Le détail d'un livre est demandé");
        return livre;
    }

}
