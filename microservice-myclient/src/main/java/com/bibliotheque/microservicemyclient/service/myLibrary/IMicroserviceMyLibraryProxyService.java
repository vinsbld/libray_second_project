package com.bibliotheque.microservicemyclient.service.myLibrary;

import com.bibliotheque.microservicemyclient.bean.CopieBean;
import com.bibliotheque.microservicemyclient.bean.CopieBeanDTO;
import com.bibliotheque.microservicemyclient.bean.EmpruntBean;
import com.bibliotheque.microservicemyclient.bean.LivreBean;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IMicroserviceMyLibraryProxyService {

    List<LivreBean> ListeDeLivres();

    LivreBean afficherUnLivre(Long id);

    List<CopieBeanDTO> afficherLesCopiesDunLivre(Long id);

    CopieBean afficherUneCopie(Long id);

    List<EmpruntBean> afficherLaListeDesReservationsParUtilisateur(Long id);

    List<CopieBean> afficherLesCopiesDisponibles(Long id);

    List<CopieBean> afficherLesCopiesNonDisponibles(Long id);

    void demandeDeReservation(Long id, Long idUtilisateur);

    EmpruntBean prolongerPret(Long id, Long idUtilisateur);

    EmpruntBean afficherUneReservation(Long id);

    List<LivreBean> faireUneRechercheParTitre(@RequestParam(name = "mc", defaultValue = "") String mc);

}
