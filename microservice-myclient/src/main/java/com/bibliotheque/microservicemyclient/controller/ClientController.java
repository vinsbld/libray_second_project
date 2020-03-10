package com.bibliotheque.microservicemyclient.controller;

import com.bibliotheque.microservicemyclient.bean.CopieBean;
import com.bibliotheque.microservicemyclient.bean.LivreBean;
import com.bibliotheque.microservicemyclient.bean.ReservationBean;
import com.bibliotheque.microservicemyclient.bean.UtilisateurBean;
import com.bibliotheque.microservicemyclient.proxies.MicroserviceMyLibraryProxy;
import com.bibliotheque.microservicemyclient.proxies.MicroserviceMyUsersProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ClientController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MicroserviceMyUsersProxy microserviceMyUsersProxy;

    @Autowired
    MicroserviceMyLibraryProxy microserviceMyLibraryProxy;

    @GetMapping("/acceuil")
    public String acceuil(Model model){

        logger.info("Page d'acceuil demandée");

        return "acceuil";

    }

    @GetMapping("/profil")
    public String afficherUnProfilUtilisateur(Model model){

        UtilisateurBean utilisateurBean = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateurBean);

        List<ReservationBean>reservationBeans = microserviceMyLibraryProxy.afficherLaListeDesReservationsParUtilisateur(utilisateurBean.getId());
        model.addAttribute("listReservations", reservationBeans);

        logger.info("L'utilisateur "+utilisateurBean+" id : "+utilisateurBean.getId()+ " consulte sa page profil");

        return "Profil";
    }

    @GetMapping("/livres")
    public String afficherUneListeDeLivres(Model model){

       List<LivreBean> livreBeanList = microserviceMyLibraryProxy.ListeDeLivres();
       model.addAttribute("livreBeanList", livreBeanList);

       logger.info("Liste de livre demandée");

       return "Livres";
    }

    @GetMapping("/livre/{id}")
    public String afficherUnLivre(Model model, @PathVariable("id") Long id){
        LivreBean livreBean = microserviceMyLibraryProxy.afficherUnLivre(id);
        model.addAttribute("livre", livreBean);
        List<CopieBean> copieBeanList = microserviceMyLibraryProxy.afficherLesCopiesLivre(livreBean.getId());
        model.addAttribute("copie", copieBeanList);
        return "Livre";
    }


}
