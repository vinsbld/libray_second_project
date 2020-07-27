package com.bibliotheque.microservicemylibrary.outils;

import lombok.*;

import java.util.Date;


@NoArgsConstructor
public @Data  class EmailType {

    @NonNull String email;
    @NonNull String titre;
    @NonNull String dateDeFinEmprunt;

    public EmailType(@NonNull String email, @NonNull String titre, @NonNull String dateDeFinEmprunt) {
        this.email = email;
        this.titre = titre;
        this.dateDeFinEmprunt = dateDeFinEmprunt;
    }
}