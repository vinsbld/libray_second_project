package com.bibliotheque.microservicemylibrary.service.userbean;

import com.bibliotheque.microservicemylibrary.beans.UtilisateurBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUserBeanServiceImpl implements IUserbeanService {

    @Autowired
    IUserbeanService iUserbeanService;

    /**
     * permet de trouver un utilisateur
     * @param id
     * @return l'utilisateur
     */
    @Override
    public UtilisateurBean findById(Long id) {
        return iUserbeanService.findById(id);
    }
}
