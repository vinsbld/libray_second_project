package com.bibliotheque.microservicemyclient.proxies;

import com.bibliotheque.microservicemyclient.bean.*;
import com.bibliotheque.microservicemyclient.configurations.FeignConfig;
import com.bibliotheque.microservicemyclient.dto.CopieBeanDTO;
import com.bibliotheque.microservicemyclient.dto.EmpruntBeanDTO;
import com.bibliotheque.microservicemyclient.dto.ReservationBeanDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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
    CopieBeanDTO afficherUneCopie(@PathVariable("id")Long id);

    @GetMapping(value = "/copies/dispos/{id}")
    List<CopieBean> afficherLesCopiesDisponibles(@PathVariable("id") Long id);

    /*===========================** Emprunts **===========================*/

    @GetMapping(value = "/listeDesEmprunts/{id}")
    List<EmpruntBeanDTO> afficherLaListeDesEmpruntsParUtilisateur(@PathVariable("id") Long id);

    @GetMapping(value = "/emprunt/{id}")
    EmpruntBean afficherUnEmprunt(@PathVariable("id")Long id);

    @PostMapping(value = "/emprunter/{id}")
    void demandeEmprunt(@PathVariable Long id, @RequestParam Long idUtilisateur);

    @PostMapping(value = "/prolonger/{id}")
    EmpruntBean prolongerEmprunt(@PathVariable Long id, @RequestParam Long idUtilisateur);

    /*===========================** Reservation **===========================*/

    @PostMapping(value = "/reserver/{id}")
    void demandeDeReservation(@PathVariable Long id, @RequestParam Long idUtilisateur);

    @GetMapping(value = "/listeDesReservations/{id}")
    List<ReservationBeanDTO> afficherlesReservationsParUtilisateur(@PathVariable("id") Long id);

    @RequestMapping(value = "/annuler/reserver/{id}", method = RequestMethod.POST)
    public void annulerReservation(@PathVariable("id") Long id, @RequestParam Long idUtilisateur);

}