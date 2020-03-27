package com.bibliotheque.microservicemyclient.service.myUsers;

import com.bibliotheque.microservicemyclient.bean.UtilisateurBean;
import com.bibliotheque.microservicemyclient.proxies.IMicroserviceMyUsersProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IMicroserviceMyUsersProxyServiceImpl implements IMicroserviceMyUsersProxyService {

    @Autowired
    private IMicroserviceMyUsersProxy iMicroserviceMyUsersProxy;

    /**
     * Permet de rechercher un utilisateur
     * @param pseudo Pseudo de l'utilisateur
     * @return l'utilisateur
     */
    @Override
    public UtilisateurBean connexionUtilisateur(String pseudo){
        return iMicroserviceMyUsersProxy.connexionUtilisateur(pseudo);
    }

    /**
     * Permet de rechecher un utilisateur
     * @param id identifiant de l'utilisateur
     * @return l'utilisateur
     */
    @Override
    public UtilisateurBean findById(Long id){
        return iMicroserviceMyUsersProxy.findById(id);
    }
}
