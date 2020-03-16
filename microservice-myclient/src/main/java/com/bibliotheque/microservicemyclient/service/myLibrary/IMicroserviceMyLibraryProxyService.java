package com.bibliotheque.microservicemyclient.service.myLibrary;

import com.bibliotheque.microservicemyclient.bean.CopieBean;
import com.bibliotheque.microservicemyclient.bean.LivreBean;
import com.bibliotheque.microservicemyclient.bean.ReservationBean;

import java.util.Date;
import java.util.List;

public interface IMicroserviceMyLibraryProxyService {

    List<LivreBean> ListeDeLivres();

    LivreBean afficherUnLivre(Long id);

    List<CopieBean> afficherLesCopiesDunLivre(Long id);

    CopieBean afficherUneCopie(Long id);

    List<ReservationBean> afficherLaListeDesReservationsParUtilisateur(Long id);

    List<CopieBean> afficherLesCopiesDisponibles(Long id);

    Date add4Weeks(Date date);

    void demandeDeReservation(Long id, ReservationBean reservationBean);
}
