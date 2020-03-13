package com.bibliotheque.microservicemyusers.service;

import com.bibliotheque.microservicemyusers.dao.IUtilisateurDao;
import com.bibliotheque.microservicemyusers.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IUtilisateurServiceImpl implements IUtilisateurService {

    @Autowired
    private IUtilisateurDao iUtilisateurDao;

    @Override
    public Utilisateur findByPseudo(String pseudo){
        return iUtilisateurDao.findByPseudo(pseudo);
    }

    @Override
    public Optional<Utilisateur> findById(Long id){
        return iUtilisateurDao.findById(id);
    }
}
