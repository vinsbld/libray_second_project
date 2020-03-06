package com.bibliotheque.microservicemyclient.controller;

import com.bibliotheque.microservicemyclient.bean.LivreBean;
import com.bibliotheque.microservicemyclient.bean.UtilisateurBean;
import com.bibliotheque.microservicemyclient.proxies.MicroserviceMyLibraryProxy;
import com.bibliotheque.microservicemyclient.proxies.MicroserviceMyUsersProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    MicroserviceMyUsersProxy microserviceMyUsersProxy;

    @Autowired
    MicroserviceMyLibraryProxy microserviceMyLibraryProxy;

    @GetMapping("/acceuil")
    public String acceuil(Model model){

        return "acceuil";

    }

    @GetMapping("/profil")
    public String afficherUnProfilUtilisateur(Model model){
        UtilisateurBean utilisateurBean = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateurBean);
        return "Profil";
    }

    @GetMapping("/livres")
    public String afficherUneListeDeLivres(Model model){
       List<LivreBean> livreBeanList = microserviceMyLibraryProxy.ListeDeLivres();
       model.addAttribute("livreBeanList", livreBeanList);
       return "Livres";
    }


}
