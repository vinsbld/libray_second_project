package com.bibliotheque.microservicemyclient.service.myLibrary;

import com.bibliotheque.microservicemyclient.bean.*;
import com.bibliotheque.microservicemyclient.proxies.IMicroserviceMyLibraryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class IMicroserviceMyLibraryProxyServiceImpl implements IMicroserviceMyLibraryProxyService {

    @Autowired
    IMicroserviceMyLibraryProxy iMicroserviceMyLibraryProxy;

    /**
     *Permet la recherche de tous les livres
     * @return la liste de tous les livres
     */


    @Override
    public List<LivreBean> ListeDeLivres(){
        return iMicroserviceMyLibraryProxy.ListeDeLivres();
    }

    /**
     * Permet la recherche d'un livre
     * @param id du livre
     * @return le livre recherché
     */
    @Override
    public LivreBean afficherUnLivre(Long id){
        return iMicroserviceMyLibraryProxy.afficherUnLivre(id);
    }


    /**
     * permet de rechercher les copies d'un livre
     * @param id identifiant du livre
     * @return la liste des copies d'un livre
     */
    @Override
    public List<CopieBeanDTO> afficherLesCopiesDunLivre(Long id) {
        return iMicroserviceMyLibraryProxy.afficherLesCopiesDunLivre(id);
    }


    /**
     * Permet de rechercher une copie
     * @param id identifiant de la copie
     * @return la copie recherchée
     */
    @Override
    public CopieBean afficherUneCopie(Long id){
        return iMicroserviceMyLibraryProxy.afficherUneCopie(id);
    }



    /**
     * Permet de rechercher les emprunts d'un utilisateur
     * @param id identifiant de l'utilisateur
     * @return la liste des emprunts d'un utilisateur
     */
    @Override
    public List<EmpruntBean> afficherLaListeDesEmpruntsParUtilisateur(Long id) {
        return iMicroserviceMyLibraryProxy.afficherLaListeDesEmpruntsParUtilisateur(id);
    }

    /**
     * Permet rechercher les copies disponibles d'un livre
     * @param id identifiant du livre
     * @return la liste des copies disponibles
     */
    @Override
    public List<CopieBean> afficherLesCopiesDisponibles(Long id){
        return iMicroserviceMyLibraryProxy.afficherLesCopiesDisponibles(id);
    }

    /**
     * Permet de faire une demande d'emprunt d'une copie d'un livre
     * @param id identifiant de la copie du livre
     * @param idUtilisateur identifiant de l'utilisateur
     */
    @Override
    public void demandeEmprunt(Long id, Long idUtilisateur) {
    iMicroserviceMyLibraryProxy.demandeEmprunt(id, idUtilisateur);
    }

    /**
     * Permet de prolonger un prêt
     * @param id identifiant du prêt
     * @param idUtilisateur identifiant de l'utilisateur
     * @return le prêt prolongé
     */
    @Override
    public EmpruntBean prolongerEmprunt(Long id, Long idUtilisateur) {
        return iMicroserviceMyLibraryProxy.prolongerEmprunt(id, idUtilisateur);
    }

    /**
     * Permet de rechercher un emprunt
     * @param id identifiant de l'emprunt
     * @return l'emprunt recherchée
     */
    @Override
    public EmpruntBean afficherUnEmprunt(Long id) {
        return iMicroserviceMyLibraryProxy.afficherUnEmprunt(id);
    }

    /**
     * Permet de rechercher un livre par son titre
     * @param mc mot clé de la recherche
     * @return la liste du résultat de la recherche
     */
    @Override
    public List<LivreBean> faireUneRechercheParTitre(@RequestParam(name = "mc", defaultValue = "") String mc){
        return iMicroserviceMyLibraryProxy.faireUneRechercheParTitre(mc);
    }

    /**
     * Permet de faire une demande de réservation d'une copie d'un livre
     * @param id identifiant de la copie du livre
     * @param idUtilisateur identifiant de l'utilisateur
     */
    @Override
    public void demandeDeReservation(Long id, Long idUtilisateur) {
        iMicroserviceMyLibraryProxy.demandeDeReservation(id, idUtilisateur);
    }

    /**
     * Permet de rechercher les réservations d'un utilisateur
     * @param id identifiant de l'utilisateur
     * @return la liste des réservations d'un utilisateur
     */
    @Override
    public List<ReservationBeanDTO> afficherlesReservationsParUtilisateur(Long id) {
        return iMicroserviceMyLibraryProxy.afficherlesReservationsParUtilisateur(id);
    }

    /**
     * permet d'afficher les réservations par copie de livre
     * @param id identifiant de la copie
     * @return la liste des réservations pour la copie
     */
    @Override
    public List<ReservationBean> afficherLesreservationsParCopie(Long id) {
        return iMicroserviceMyLibraryProxy.afficherLesreservationsParCopie(id);
    }


}
