package com.bibliotheque.microservicemyclient.service.myUsers;

import com.bibliotheque.microservicemyclient.bean.UtilisateurBean;
import org.springframework.web.bind.annotation.PathVariable;

public interface IMicroserviceMyUsersProxyService {

    UtilisateurBean connexionUtilisateur(@PathVariable String pseudo);
}
