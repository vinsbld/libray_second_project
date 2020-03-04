package com.bibliotheque.microservicemyclient.security;

import com.bibliotheque.microservicemyclient.bean.UtilisateurBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurSecurityService implements UserDetailsService {



    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        UtilisateurBean utilisateur = utilisateurDao.findByPseudo(pseudo.toLowerCase());
        if (utilisateur==null){
            throw new UsernameNotFoundException("l'utilisateur "+pseudo+" n'existe pas");
        }else {
            return utilisateur;
        }
    }
}
