package com.bibliotheque.microservicemyclient.controller;

import com.bibliotheque.microservicemyclient.bean.LivreBean;
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


}
