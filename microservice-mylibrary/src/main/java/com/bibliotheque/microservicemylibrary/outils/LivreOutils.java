package com.bibliotheque.microservicemylibrary.outils;

import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import com.bibliotheque.microservicemylibrary.service.livre.ILivreService;
import com.bibliotheque.microservicemylibrary.service.reservation.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class LivreOutils {

    @Autowired
    private ILivreService iLivreService;

    @Autowired
    private IEmpruntService iEmpruntService;

    @Autowired
    private IReservationService iReservationService;

    @Autowired
    private ICopieService iCopieService;

    public void dateDeRetourLaPlusProche(Livre livre){

        List<Date> dates = new ArrayList<Date>();
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
            livre.setDateRetourLaPlusProche((dateLaPlusProche));
        }


    }
}
