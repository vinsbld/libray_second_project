package com.bibliotheque.microservicemyclient.proxies;

import com.bibliotheque.microservicemyclient.bean.CopieBean;
import com.bibliotheque.microservicemyclient.bean.LivreBean;
import com.bibliotheque.microservicemyclient.bean.ReservationBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;


@FeignClient(name ="microservice-mylibrary")
@RibbonClient(name ="microservice-mylibrary")
public interface MicroserviceMyLibraryProxy {

    @GetMapping(value = "/livres")
    List<LivreBean> ListeDeLivres();

    @GetMapping(value = "/livre/{id}")
    LivreBean afficherUnLivre(@PathVariable("id") Long id);

    @GetMapping(value = "/copies/{id}")
    List<CopieBean> afficherLesCopiesLivre(@PathVariable Long id);

    @GetMapping("/listeCopies")
    List<CopieBean> afficherUneListeDeCopie();

    @GetMapping(value = "/listeDesReservations/{id}")
    List<ReservationBean> afficherLaListeDesReservationsParUtilisateur(@PathVariable("id")Long id);

}