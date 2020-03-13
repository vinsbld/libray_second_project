package com.bibliotheque.microservicemyclient.service.myUsers;

import com.bibliotheque.microservicemyclient.bean.UtilisateurBean;

public interface IMicroserviceMyUsersProxyService {

    UtilisateurBean connexionUtilisateur(String pseudo);
}
