package com.bibliotheque.microservicemylibrary.beans;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public @Data
class UtilisateurBean {

    private Long id;

    private String pseudo;

    private String motDePasse;

    private String email;
}
