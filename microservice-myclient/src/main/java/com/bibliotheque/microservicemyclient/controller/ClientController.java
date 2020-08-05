package com.bibliotheque.microservicemyclient.controller;

import com.bibliotheque.microservicemyclient.bean.*;
import com.bibliotheque.microservicemyclient.exeptions.CannotAddBookingException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        List<EmpruntBean> empruntBeans = iMicroserviceMyLibraryProxyService.afficherLaListeDesEmpruntsParUtilisateur(utilisateurBean.getId());
        model.addAttribute("empruntList", empruntBeans);

        List<ReservationBeanDTO> reservationBeans = iMicroserviceMyLibraryProxyService.afficherlesReservationsParUtilisateur(utilisateurBean.getId());
        model.addAttribute("reservationList", reservationBeans);

        logger.info("L'utilisateur "+utilisateurBean+" id : "+utilisateurBean.getId()+ " consulte sa page profil");

        return "Profil";
    }

    /*============== #Livres ======================*/
    //afficher une liste de tous les livres
    @GetMapping("/livres")
    public String afficherUneListeDeLivres(Model model){

       List<LivreBean> livreBeanList = iMicroserviceMyLibraryProxyService.ListeDeLivres();
       model.addAttribute("livreBeanList", livreBeanList);

       logger.info("Liste de livre demandée");

       return "Livres";
    }

    //afficher un livre
    @GetMapping("/livre/{id}")
    public String afficherUnLivre(Model model, @PathVariable("id") Long id){

        LivreBean livreBean = iMicroserviceMyLibraryProxyService.afficherUnLivre(id);
        model.addAttribute("livreBean", livreBean);

        List<CopieBean> copieBeansDisponibles = iMicroserviceMyLibraryProxyService.afficherLesCopiesDisponibles(livreBean.getId());
        model.addAttribute("copieBeansDisponibles", copieBeansDisponibles);
        model.addAttribute("nbCopiesDisponibles", copieBeansDisponibles.size());

        List<CopieBeanDTO> copiesDunLivre = iMicroserviceMyLibraryProxyService.afficherLesCopiesDunLivre(id);
        model.addAttribute("nbTTCopies", copiesDunLivre.size());
        model.addAttribute("mCopies", copiesDunLivre);

        logger.info("Le livre "+livreBean.getTitre()+" est en consultation");

        return "Livre";
    }

    //faire une recherche de livre
    @GetMapping("/recherche")
    public String rechercherUnLivre(Model model, @RequestParam(name = "mc", defaultValue = "") String mc){

        logger.info("un utilisateur effectue une recherche de livre avec le mot clé : "+ mc );

        if (mc.isEmpty()){
            List<LivreBean> livreBeanList = iMicroserviceMyLibraryProxyService.ListeDeLivres();
            model.addAttribute("livreBeanList", livreBeanList);
        }
        try {

            List<LivreBean> livreBeanList = iMicroserviceMyLibraryProxyService.faireUneRechercheParTitre("%" + mc + "%");
            model.addAttribute("livreBeanList", livreBeanList);
            model.addAttribute("mc", mc);
        }catch (Exception e){
            model.addAttribute("exception",e);
            throw new RuntimeException("Livre Introuvable");
        }

        return "/Livres";
    }

    /*============== #Emprunt ======================*/
    //faire un emprunt
    @PostMapping("/emprunter/{id}")
    public String demandeEmprunt(Model model, @PathVariable("id")Long id){

        UtilisateurBean utilisateurBean = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        utilisateurBean = iMicroserviceMyUsersProxyService.findById(utilisateurBean.getId());
        model.addAttribute("utilisateurBean", utilisateurBean);

        CopieBean copieBean = iMicroserviceMyLibraryProxyService.afficherUneCopie(id);
        model.addAttribute("copie", copieBean);

        iMicroserviceMyLibraryProxyService.demandeEmprunt(copieBean.getId(), utilisateurBean.getId());

        logger.info("l'utilisateur : "+utilisateurBean.getPseudo()+ " id : " +utilisateurBean.getId()+" fait une demande d'emprunt pour la copie isbn : "+copieBean.getIsbn());

        return "redirect:/livres";
    }

    //prolonger un pret
    @PostMapping("/prolonger/{id}")
    public String prolongerLePret(Model model, @PathVariable("id")Long id){

        UtilisateurBean utilisateurBean = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateurBean", utilisateurBean);

        EmpruntBean empruntBean = iMicroserviceMyLibraryProxyService.afficherUnEmprunt(id);
        iMicroserviceMyLibraryProxyService.prolongerEmprunt(empruntBean.getId(), utilisateurBean.getId());

        logger.info("l'utilisateur : "+utilisateurBean.getPseudo()+" a prolonger le prêt dont l' id est : "+ empruntBean.getId());

        return "redirect:/profil";

    }

    /*============== #Reservation ======================*/
    @PostMapping("/reserver")
    public String demandeDeReservation(Model model, @RequestParam("id") Long id, RedirectAttributes redirectAttributes){

        try {
            UtilisateurBean utilisateurBean = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            utilisateurBean = iMicroserviceMyUsersProxyService.findById(utilisateurBean.getId());
            model.addAttribute("utilisateurBean", utilisateurBean);

            CopieBean copieBean = iMicroserviceMyLibraryProxyService.afficherUneCopie(id);
            model.addAttribute("copie", copieBean);

            iMicroserviceMyLibraryProxyService.demandeDeReservation(copieBean.getId(), utilisateurBean.getId());

            String messageOK = "votre de mande de réservation a été réalisé avec succes.";
            redirectAttributes.addFlashAttribute("messageOK", messageOK);

            logger.info("l'utilisateur : "+utilisateurBean.getPseudo()+ " id : " +utilisateurBean.getId()+" fait une demande de réservtion pour la copie isbn : "+copieBean.getIsbn());

        }catch (Exception e){
            e.printStackTrace();
            if (e instanceof CannotAddBookingException){
                String message = e.getMessage();
                redirectAttributes.addFlashAttribute("messageErreur", message);
            }
        }

        return "redirect:/profil";
    }


}
