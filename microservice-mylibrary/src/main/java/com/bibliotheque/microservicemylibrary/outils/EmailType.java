package com.bibliotheque.microservicemylibrary.outils;

import lombok.*;

import java.util.Date;


@NoArgsConstructor
public @Data  class EmailType {

    @NonNull String email;
    @NonNull String titre;
    @NonNull String dateDeFinDuPret;

    public EmailType(@NonNull String email, @NonNull String titre, @NonNull String dateDeFinDuPret) {
        this.email = email;
        this.titre = titre;
        this.dateDeFinDuPret = dateDeFinDuPret;
    }
}