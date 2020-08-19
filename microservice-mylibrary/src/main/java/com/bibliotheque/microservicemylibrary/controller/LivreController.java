package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.exeptions.LivresNotFoundException;
import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import com.bibliotheque.microservicemylibrary.service.livre.ILivreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
public class LivreController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ILivreService iLivreService;

    @Autowired
    private ICopieService iCopieService;

    @Autowired
    private IEmpruntService iEmpruntService;

    @RequestMapping(value = "/livres")
    public List<Livre> ListeDeLivres(){
        List<Livre> livres = iLivreService.findAll();
        if (livres.isEmpty()) throw new LivresNotFoundException("Il n'y a pas de livres");
        logger.info("Récupération de la liste des produits");
        return livres;
    }

    @RequestMapping(value = "/livre/{id}")
    public Optional<Livre> afficherUnLivre(@PathVariable("id") Long id) {
        Optional<Livre> livre = iLivreService.findById(id);

        List<Date> dates = new ArrayList<>();
        List<Copie> copies = iCopieService.findAllByLivreId(livre.get().getId());
        for (Copie c : copies) {
            List<Emprunt> emprunts = iEmpruntService.findAllByCopie_IdAndDateRetourIsNull(c.getId());
            if (emprunts.size() > 0){
                Emprunt emprunt = emprunts.get(0);
                dates.add(emprunt.getDateDeFinEmprunt());
            }
        }
        Collections.sort(dates);
        if (dates.size() > 0){
            Date dateLaPlusProche = dates.get(0);
            livre.get().setDateRetourLaPlusProche(dateLaPlusProche);
        }

        logger.info("Le détail d'un livre est demandé");
        return livre;
    }

    @RequestMapping(value = "/addLivre", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String ajouterUnLivre(@RequestBody Livre livre){
        iLivreService.save(livre);
        logger.info("ajout d'une demande de reservation pour un livre");
        return "ok";
    }

    @RequestMapping(value = "/recherche", method = RequestMethod.GET)
    public List<Livre> faireUneRechercheParTitre(@RequestParam(name = "mc", defaultValue = "")String mc){
        logger.info("recherche pour un livre");
        return iLivreService.chercherParTitre("%"+mc+"%");
    }

}