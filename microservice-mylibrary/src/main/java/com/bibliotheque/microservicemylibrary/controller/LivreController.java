package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.model.Livre;
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

    @RequestMapping(value = "/livres")
    public List<Livre> ListeDeLivres(){
        return iLivreService.livres();
    }

    @RequestMapping(value = "/livre/{id}")
    public Optional<Livre> afficherUnLivre(@PathVariable("id") Long id) {
        return iLivreService.livre(id);
    }

    @RequestMapping(value = "/addLivre", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String ajouterUnLivre(@RequestBody Livre livre){
        iLivreService.save(livre);
        return "ok";
    }

    @RequestMapping(value = "/recherche", method = RequestMethod.GET)
    public List<Livre> faireUneRechercheParTitre(@RequestParam(name = "mc", defaultValue = "")String mc){
        logger.info("recherche pour un livre");
        return iLivreService.chercherParTitre("%"+mc+"%");
    }

}