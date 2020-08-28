package com.bibliotheque.microservicemylibrary.service.userbean;

import com.bibliotheque.microservicemylibrary.beans.UtilisateurBean;
import org.springframework.web.bind.annotation.PathVariable;

public interface IUserbeanService {

    //trouver un utilisateur par son id
    UtilisateurBean findById(@PathVariable("id") Long id);
}
