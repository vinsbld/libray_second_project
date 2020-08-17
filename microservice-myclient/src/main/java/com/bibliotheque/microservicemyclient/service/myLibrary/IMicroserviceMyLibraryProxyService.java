package com.bibliotheque.microservicemyclient.service.myLibrary;

import com.bibliotheque.microservicemyclient.bean.*;
import com.bibliotheque.microservicemyclient.dto.CopieBeanDTO;
import com.bibliotheque.microservicemyclient.dto.EmpruntBeanDTO;
import com.bibliotheque.microservicemyclient.dto.ReservationBeanDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IMicroserviceMyLibraryProxyService {

    /*===========================** Livre **===========================*/

    List<LivreBean> ListeDeLivres();

    LivreBean afficherUnLivre(Long id);

    List<LivreBean> faireUneRechercheParTitre(@RequestParam(name = "mc", defaultValue = "") String mc);

    /*===========================** Copie **===========================*/

    List<CopieBean> afficherLesCopiesDisponibles(Long id);

    List<CopieBeanDTO> afficherLesCopiesDunLivre(Long id);

    CopieBeanDTO afficherUneCopie(Long id);

    /*===========================** Emprunt **===========================*/

    void demandeEmprunt(Long id, Long idUtilisateur);

    EmpruntBean prolongerEmprunt(Long id, Long idUtilisateur);

    EmpruntBean afficherUnEmprunt(Long id);

    List<EmpruntBeanDTO> afficherLaListeDesEmpruntsParUtilisateur(Long id);

    /*===========================** Reservation **===========================*/

    void demandeDeReservation(Long id, Long idUtilisateur);

    List<ReservationBeanDTO> afficherlesReservationsParUtilisateur(Long id);

    void annulerReservation(Long id, Long idUtilisateur);


}
