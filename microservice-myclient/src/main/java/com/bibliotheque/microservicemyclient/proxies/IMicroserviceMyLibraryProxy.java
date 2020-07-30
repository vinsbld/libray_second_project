package com.bibliotheque.microservicemyclient.proxies;

import com.bibliotheque.microservicemyclient.bean.CopieBean;
import com.bibliotheque.microservicemyclient.bean.CopieBeanDTO;
import com.bibliotheque.microservicemyclient.bean.EmpruntBean;
import com.bibliotheque.microservicemyclient.bean.LivreBean;
import com.bibliotheque.microservicemyclient.configurations.FeignConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/microservice-mylibrary")
@FeignClient(name ="zuul-server", contextId = "IMicroserviceMyLibraryProxy", configuration = FeignConfig.class, url = "http://localhost:9006")
@RibbonClient(name ="microservice-mylibrary")
public interface IMicroserviceMyLibraryProxy {

    /*===========================** Livres **===========================*/
    @GetMapping(value = "/livres")
    List<LivreBean> ListeDeLivres();

    @GetMapping(value = "/livre/{id}")
    LivreBean afficherUnLivre(@PathVariable("id") Long id);

    @GetMapping(value = "/recherche")
    List<LivreBean> faireUneRechercheParTitre(@RequestParam(name = "mc", defaultValue = "")String mc);

    /*===========================** Copies **===========================*/

    @GetMapping(value = "/copies/{id}")
    List<CopieBeanDTO> afficherLesCopiesDunLivre(@PathVariable("id")Long id);

    @GetMapping(value = "/copie/{id}")
    CopieBean afficherUneCopie(@PathVariable("id")Long id);

    @GetMapping(value = "/copies/dispos/{id}")
    List<CopieBean> afficherLesCopiesDisponibles(@PathVariable("id") Long id);

    /*===========================** Emprunts **===========================*/

    @GetMapping(value = "/listeDesEmprunts/{id}")
    List<EmpruntBean> afficherLaListeDesEmpruntsParUtilisateur(@PathVariable("id") Long id);

    @GetMapping(value = "/emprunt/{id}")
    EmpruntBean afficherUnEmprunt(@PathVariable("id")Long id);

    @PostMapping(value = "/emprunter/{id}")
    void demandeEmprunt(@PathVariable Long id, @RequestParam Long idUtilisateur);

    @PostMapping(value = "/prolonger/{id}")
    EmpruntBean prolongerEmprunt(@PathVariable Long id, @RequestParam Long idUtilisateur);

    /*===========================** Reservation **===========================*/
    @PostMapping(value = "/reserver/{id}")
    void demandeDeReservation(@PathVariable Long id, @RequestParam Long idUtilisateur);

}