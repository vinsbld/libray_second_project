package com.bibliotheque.microservicemyclient.service.myUsers;

import com.bibliotheque.microservicemyclient.bean.UtilisateurBean;
import com.bibliotheque.microservicemyclient.proxies.IMicroserviceMyUsersProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class IMicroserviceMyUsersProxyServiceImpl implements IMicroserviceMyUsersProxyService {

    @Autowired
    private IMicroserviceMyUsersProxy iMicroserviceMyUsersProxy;

    @Override
    public UtilisateurBean connexionUtilisateur(@PathVariable String pseudo){
        return iMicroserviceMyUsersProxy.connexionUtilisateur(pseudo);
    }
}
