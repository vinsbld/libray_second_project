package com.bibliotheque.microservicemyclient.service.myUsers;

import com.bibliotheque.microservicemyclient.bean.UtilisateurBean;

import java.util.Optional;

public interface IMicroserviceMyUsersProxyService {

    UtilisateurBean connexionUtilisateur(String pseudo);

    Optional<UtilisateurBean> findById(Long id);
}
