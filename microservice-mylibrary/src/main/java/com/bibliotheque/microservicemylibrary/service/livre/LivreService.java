package com.bibliotheque.microservicemylibrary.service.livre;

import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import com.bibliotheque.microservicemylibrary.service.reservation.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class LivreService {

    @Autowired
    ILivreService iLivreService;

    @Autowired
    ICopieService iCopieService;

    @Autowired
    IEmpruntService iEmpruntService;

    @Autowired
    IReservationService iReservationService;

    /**
     * Fonction pour trouver la date de retour la plus proche pour un livre
     * @param livre
     */
    public void dateDeRetourLaplusProche(Livre livre){

        List<Date> dates = new ArrayList<>();
        List<Copie> copies = iCopieService.findAllByLivreId(livre.getId());
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
            livre.setDateRetourLaPlusProche(dateLaPlusProche);
        }
    }

}
