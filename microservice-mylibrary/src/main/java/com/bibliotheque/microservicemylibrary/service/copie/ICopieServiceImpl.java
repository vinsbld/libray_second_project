package com.bibliotheque.microservicemylibrary.service.copie;

import com.bibliotheque.microservicemylibrary.dao.ICopieDao;
import com.bibliotheque.microservicemylibrary.model.Copie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ICopieServiceImpl implements ICopieService {

    @Autowired
    ICopieDao iCopieDao;

    /**
     * Permet de trouver toutes les copies d'un livre
     * @param id identifiant du livre
     * @return la liste des copies d'un livre
     */
    @Override
    public List<Copie> findAllByLivreId(Long id){
        return iCopieDao.findAllByLivreId(id);
    }

    /**
     * Permet de trouver une copie
     * @param id identifiant de la copie
     * @return la copie
     */
    @Override
    public Optional<Copie> findById(Long id){
        return iCopieDao.findById(id);
    }

    /**
     * Permet d'enregitrer une copie
     * @param copie Objet à enregitrer
     * @return Objet copie
     */
    @Override
    public Copie save(Copie copie){
        return iCopieDao.save(copie);
    }

    /**
     * Permet de trouver les copies disponibles d'un livre
     * @param idLivre identifiant du livre
     * @return la liste des copies disponibles
     */
    @Override
    public List<Copie> getCopieLivresDisponibles(Long idLivre){
        return iCopieDao.getCopieLivresDisponibles(idLivre);
    }

    @Override
    public List<Copie> getCopieLivresIndisponibles(Long id) {
        return iCopieDao.getCopieLivresIndisponibles(id);
    }
}