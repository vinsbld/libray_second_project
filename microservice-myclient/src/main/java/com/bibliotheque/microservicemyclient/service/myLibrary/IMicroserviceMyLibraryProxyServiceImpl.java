package com.bibliotheque.microservicemyclient.service.myLibrary;

import com.bibliotheque.microservicemyclient.bean.CopieBean;
import com.bibliotheque.microservicemyclient.bean.LivreBean;
import com.bibliotheque.microservicemyclient.bean.ReservationBean;
import com.bibliotheque.microservicemyclient.proxies.IMicroserviceMyLibraryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class IMicroserviceMyLibraryProxyServiceImpl implements IMicroserviceMyLibraryProxyService {

    @Autowired
    IMicroserviceMyLibraryProxy iMicroserviceMyLibraryProxy;

    @Override
    public List<LivreBean> ListeDeLivres(){
        return iMicroserviceMyLibraryProxy.ListeDeLivres();
    }

    @Override
    public LivreBean afficherUnLivre(Long id){
        return iMicroserviceMyLibraryProxy.afficherUnLivre(id);
    }

    @Override
    public List<CopieBean> afficherLesCopiesDunLivre(Long id){
        return iMicroserviceMyLibraryProxy.afficherLesCopiesDunLivre(id);
    }

    @Override
    public CopieBean afficherUneCopie(Long id){
        return iMicroserviceMyLibraryProxy.afficherUneCopie(id);
    }

    @Override
    public List<ReservationBean> afficherLaListeDesReservationsParUtilisateur(Long id){
        return iMicroserviceMyLibraryProxy.afficherLaListeDesReservationsParUtilisateur(id);
    }

    @Override
    public List<CopieBean> afficherLesCopiesDisponibles(Long id){
        return iMicroserviceMyLibraryProxy.afficherLesCopiesDisponibles(id);
    }

    @Override
    public void demandeDeReservation(Long id, Long idUtilisateur){
        iMicroserviceMyLibraryProxy.demandeDeReservation(id, idUtilisateur);
    }

    @Override
    public void prolongerPret(Long id, ReservationBean reservationBean){
        iMicroserviceMyLibraryProxy.prolongerPret(id, reservationBean);
    }
}
