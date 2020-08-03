package com.bibliotheque.microservicemyclient.service.myLibrary;

import com.bibliotheque.microservicemyclient.bean.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface IMicroserviceMyLibraryProxyService {

    List<LivreBean> ListeDeLivres();

    LivreBean afficherUnLivre(Long id);

    List<CopieBeanDTO> afficherLesCopiesDunLivre(Long id);

    CopieBean afficherUneCopie(Long id);

    List<EmpruntBean> afficherLaListeDesEmpruntsParUtilisateur(Long id);

    List<CopieBean> afficherLesCopiesDisponibles(Long id);

    void demandeEmprunt(Long id, Long idUtilisateur);

    EmpruntBean prolongerEmprunt(Long id, Long idUtilisateur);

    EmpruntBean afficherUnEmprunt(Long id);

    List<LivreBean> faireUneRechercheParTitre(@RequestParam(name = "mc", defaultValue = "") String mc);

    void demandeDeReservation(Long id, Long idUtilisateur);

    List<ReservationBeanDTO> afficherlesReservationsParUtilisateur(Long id);


}
