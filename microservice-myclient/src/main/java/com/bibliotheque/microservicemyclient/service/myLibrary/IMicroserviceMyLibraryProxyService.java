package com.bibliotheque.microservicemyclient.service.myLibrary;

import com.bibliotheque.microservicemyclient.bean.CopieBean;
import com.bibliotheque.microservicemyclient.bean.CopieBeanDTO;
import com.bibliotheque.microservicemyclient.bean.EmpruntBean;
import com.bibliotheque.microservicemyclient.bean.LivreBean;
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

}
