package com.bibliotheque.microservicemyclient.security;

import com.bibliotheque.microservicemyclient.bean.UtilisateurBean;
import com.bibliotheque.microservicemyclient.proxies.MicroserviceMyUsersProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurSecurityService implements UserDetailsService {

@Autowired
MicroserviceMyUsersProxy microserviceMyUsersProxy;

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        UtilisateurBean utilisateur = microserviceMyUsersProxy.connexionUtilisateur(pseudo.toLowerCase());
        if (utilisateur==null){
            throw new UsernameNotFoundException("l'utilisateur "+pseudo+" n'existe pas");
        }else {
            return utilisateur;
        }
    }
}
