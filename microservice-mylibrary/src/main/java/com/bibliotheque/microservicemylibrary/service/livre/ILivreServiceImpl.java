package com.bibliotheque.microservicemylibrary.service.livre;

import com.bibliotheque.microservicemylibrary.dao.ILivreDao;
import com.bibliotheque.microservicemylibrary.model.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ILivreServiceImpl implements ILivreService {

    @Autowired
    ILivreDao mLivreDao;


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
    public Optional<Livre> findById(Long id){
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



}
