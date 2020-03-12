package com.bibliotheque.microservicemyusers.service;

import com.bibliotheque.microservicemyusers.model.Utilisateur;

import java.util.Optional;

public interface IUtilisateurService {

    Utilisateur findByPseudo(String pseudo);

    Optional<Utilisateur> findById(Long id);
}
