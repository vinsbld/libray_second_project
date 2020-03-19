package com.bibliotheque.microservicemyclient.controller;

import com.bibliotheque.microservicemyclient.bean.CopieBean;
import com.bibliotheque.microservicemyclient.bean.LivreBean;
import com.bibliotheque.microservicemyclient.bean.ReservationBean;
import com.bibliotheque.microservicemyclient.bean.UtilisateurBean;
import com.bibliotheque.microservicemyclient.service.myLibrary.IMicroserviceMyLibraryProxyService;
import com.bibliotheque.microservicemyclient.service.myUsers.IMicroserviceMyUsersProxyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class ClientController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IMicroserviceMyUsersProxyService iMicroserviceMyUsersProxyService;

    @Autowired
    IMicroserviceMyLibraryProxyService iMicroserviceMyLibraryProxyService;

    @GetMapping("/acceuil")
    public String acceuil(Model model){

        logger.info("Page d'acceuil demandée");

        return "acceuil";

    }

    /*============== #Profil ======================*/
    @GetMapping("/profil")
    public String afficherUnProfilUtilisateur(Model model){

        UtilisateurBean utilisateurBean = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateurBean", utilisateurBean);

        List<ReservationBean>reservationBeans = iMicroserviceMyLibraryProxyService.afficherLaListeDesReservationsParUtilisateur(utilisateurBean.getId());
        model.addAttribute("reservationBeans", reservationBeans);

        logger.info("L'utilisateur "+utilisateurBean+" id : "+utilisateurBean.getId()+ " consulte sa page profil");

        return "Profil";
    }

    /*============== #Livres ======================*/
    @GetMapping("/livres")
    public String afficherUneListeDeLivres(Model model){

       List<LivreBean> livreBeanList = iMicroserviceMyLibraryProxyService.ListeDeLivres();
       model.addAttribute("livreBeanList", livreBeanList);

       logger.info("Liste de livre demandée");

       return "Livres";
    }

    @GetMapping("/livre/{id}")
    public String afficherUnLivre(Model model, @PathVariable("id") Long id){

        LivreBean livreBean = iMicroserviceMyLibraryProxyService.afficherUnLivre(id);
        model.addAttribute("livreBean", livreBean);

        List<CopieBean> copieBeansDisponibles = iMicroserviceMyLibraryProxyService.afficherLesCopiesDisponibles(livreBean.getId());
        model.addAttribute("copieBeansDisponibles", copieBeansDisponibles);
        model.addAttribute("nbCopiesDisponibles", copieBeansDisponibles.size());

        List<CopieBean> nbTTCopies= iMicroserviceMyLibraryProxyService.afficherLesCopiesDunLivre(id);
        model.addAttribute("nbTTCopies", nbTTCopies.size());

        logger.info("Le livre "+livreBean.getTitre()+" est en consultation");

        return "Livre";
    }

    /*============== #Reservation ======================*/
    @PostMapping("/reservation/{id}")
    public String demandeDeReservation(Model model, @PathVariable("id")Long id){

        UtilisateurBean utilisateurBean = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        utilisateurBean = iMicroserviceMyUsersProxyService.findById(utilisateurBean.getId());
        model.addAttribute("utilisateurBean", utilisateurBean);

        CopieBean copieBean = iMicroserviceMyLibraryProxyService.afficherUneCopie(id);
        model.addAttribute("copie", copieBean);

        iMicroserviceMyLibraryProxyService.demandeDeReservation(copieBean.getId(), utilisateurBean.getId());

        return "redirect:/livres";
    }

    //prolonger un pret
    @PostMapping("/prolonger/{id}")
    public String prolongerLePret(Model model, @PathVariable("id")Long id){

        UtilisateurBean utilisateurBean = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateurBean", utilisateurBean);

        ReservationBean reservationBean = iMicroserviceMyLibraryProxyService.affivherUneReservation(id);
        iMicroserviceMyLibraryProxyService.prolongerPret(reservationBean.getId(), utilisateurBean.getId());

        return "redirect:/profil";

    }


}
