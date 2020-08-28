package com.bibliotheque.microservicemylibrary.service.copie;

import com.bibliotheque.microservicemylibrary.dao.ICopieDao;
import com.bibliotheque.microservicemylibrary.dto.CopieDTO;
import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import com.bibliotheque.microservicemylibrary.service.livre.ILivreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ICopieServiceImpl implements ICopieService {

    @Autowired
    private ICopieDao iCopieDao;

    @Autowired
    private IEmpruntService iEmpruntService;

    @Autowired
    private ILivreService iLivreService;

    Logger logger = LoggerFactory.getLogger(this.getClass());


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

    /**
     * permet de trouver les copies indisponible d'un livre
     * @param id identifiant du livre
     * @return la liste des copies indisponibles
     */
    @Override
    public List<Copie> getCopieLivresIndisponibles(Long id) {
        return iCopieDao.getCopieLivresIndisponibles(id);
    }

    /**
     * permet d'afficher les copies d'un livre
     * @param id identifiant du livre
     * @return la liste des copies
     */
    @Override
    public List<CopieDTO> afficherLesCopies(Long id) {
        List<Copie>copieList = iCopieDao.findAllByLivreId(id);
        List<CopieDTO> copieDTOs = new ArrayList<>();
        for (Copie c : copieList) {
            CopieDTO cp = new CopieDTO();
            cp.setCopie(c);
            cp.setIsbn(c.getIsbn());
            cp.setId(c.getId());
            cp.setLivre(c.getLivre());
            Emprunt r = iEmpruntService.findByCopie_Id(c.getId());
            cp.setEmprunt(r);
            copieDTOs.add(cp);
        }
        logger.info("demande d'une liste de copies d'un livre");
        return copieDTOs;
    }

    /**
     * permet de d'afficher une copie
     * @param id identifiant de la copie
     * @return la copie
     */
    @Override
    public CopieDTO voirCopie(Long id) {
        Optional<Copie> copie = iCopieDao.findById(id);
        Optional<Livre> livre = iLivreService.findByCopiesId(copie.get().getId());
        CopieDTO copieDTO = new CopieDTO();
        copieDTO.setLivre(livre.get());
        copieDTO.setCopie(copie.get());
        copieDTO.setId(copie.get().getId());
        copieDTO.setIsbn(copie.get().getIsbn());
        Emprunt r = iEmpruntService.findByCopie_Id(copie.get().getId());
        copieDTO.setEmprunt(r);
        logger.info("demande d'une copie d'un livre");
        return copieDTO;
    }

    /**
     * permet d'afficher les copies disponibles
     * @param id identifiant du livre
     * @return la liste des copies disponibles
     */
    @Override
    public List<Copie> voirLesCopiesDisponibles(Long id) {
        List<Copie> copiesDisponibles = getCopieLivresDisponibles(id);
        logger.info("demande des copies disponibles pour un livre");
        return copiesDisponibles;
    }

    /**
     * permet d'afficher les copies indisponibles
     * @param id identifiant du livre
     * @return la liste des copies indisponibles
     */
    @Override
    public List<Copie> voirLesCopiesNonDisponibles(Long id) {
        List<Copie> copiesNonDispos = getCopieLivresIndisponibles(id);
        return copiesNonDispos;
    }

}