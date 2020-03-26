package com.bibliotheque.microservicemyusers.service;

import com.bibliotheque.microservicemyusers.model.Utilisateur;

import java.util.Optional;

public interface IUtilisateurService {

    //trouve un utilisateur par son pseudo
    Utilisateur findByPseudo(String pseudo);

    //trouve un utilisateur pas son id
    Optional<Utilisateur> findById(Long id);
}
