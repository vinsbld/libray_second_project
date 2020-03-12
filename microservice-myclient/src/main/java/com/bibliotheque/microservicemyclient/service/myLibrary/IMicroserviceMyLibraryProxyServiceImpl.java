package com.bibliotheque.microservicemyclient.service.myLibrary;

import com.bibliotheque.microservicemyclient.bean.CopieBean;
import com.bibliotheque.microservicemyclient.bean.LivreBean;
import com.bibliotheque.microservicemyclient.bean.ReservationBean;
import com.bibliotheque.microservicemyclient.proxies.IMicroserviceMyLibraryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class IMicroserviceMyLibraryProxyServiceImpl implements IMicroserviceMyLibraryProxyService {

    @Autowired
    IMicroserviceMyLibraryProxy IMicroserviceMyLibraryProxy;

    @Override
    public List<LivreBean> ListeDeLivres(){
        return IMicroserviceMyLibraryProxy.ListeDeLivres();
    }

    @Override
    public LivreBean afficherUnLivre(@PathVariable("id") Long id){
        return IMicroserviceMyLibraryProxy.afficherUnLivre(id);
    }

    @Override
    public List<CopieBean> afficherLesCopiesDunLivre(@PathVariable("id")Long id){
        return IMicroserviceMyLibraryProxy.afficherLesCopiesDunLivre(id);
    }

    @Override
    public CopieBean afficherUneCopie(@PathVariable("id")Long id){
        return IMicroserviceMyLibraryProxy.afficherUneCopie(id);
    }

    @Override
    public List<ReservationBean> afficherLaListeDesReservationsParUtilisateur(@PathVariable("id")Long id){
        return IMicroserviceMyLibraryProxy.afficherLaListeDesReservationsParUtilisateur(id);
    }

}
