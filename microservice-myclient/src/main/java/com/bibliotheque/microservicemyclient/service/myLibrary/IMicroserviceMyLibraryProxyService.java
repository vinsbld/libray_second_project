package com.bibliotheque.microservicemyclient.service.myLibrary;

import com.bibliotheque.microservicemyclient.bean.CopieBean;
import com.bibliotheque.microservicemyclient.bean.CopieBeanDTO;
import com.bibliotheque.microservicemyclient.bean.LivreBean;
import com.bibliotheque.microservicemyclient.bean.ReservationBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IMicroserviceMyLibraryProxyService {

    List<LivreBean> ListeDeLivres();

    LivreBean afficherUnLivre(Long id);

    List<CopieBeanDTO> afficherLesCopiesDunLivre(Long id);

    CopieBean afficherUneCopie(Long id);

    List<ReservationBean> afficherLaListeDesReservationsParUtilisateur(Long id);

    List<CopieBean> afficherLesCopiesDisponibles(Long id);

    List<CopieBean> afficherLesCopiesNonDisponibles(Long id);

    void demandeDeReservation(Long id, Long idUtilisateur);

    ReservationBean prolongerPret(Long id, Long idUtilisateur);

    ReservationBean afficherUneReservation(Long id);

    List<LivreBean> faireUneRechercheParTitre(@RequestParam(name = "mc", defaultValue = "") String mc);

}
