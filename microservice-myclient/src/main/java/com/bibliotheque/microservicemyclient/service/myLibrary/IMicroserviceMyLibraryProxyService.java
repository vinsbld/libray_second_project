package com.bibliotheque.microservicemyclient.service.myLibrary;

import com.bibliotheque.microservicemyclient.bean.CopieBean;
import com.bibliotheque.microservicemyclient.bean.LivreBean;
import com.bibliotheque.microservicemyclient.bean.ReservationBean;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IMicroserviceMyLibraryProxyService {

    List<LivreBean> ListeDeLivres();

    LivreBean afficherUnLivre(@PathVariable("id") Long id);

    List<CopieBean> afficherLesCopiesDunLivre(@PathVariable("id") Long id);

    CopieBean afficherUneCopie(@PathVariable("id") Long id);

    List<ReservationBean> afficherLaListeDesReservationsParUtilisateur(@PathVariable("id") Long id);
}
