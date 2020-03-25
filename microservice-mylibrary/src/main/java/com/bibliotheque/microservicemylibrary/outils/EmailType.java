package com.bibliotheque.microservicemylibrary.outils;

import lombok.*;

import java.util.Date;


@NoArgsConstructor
@RequiredArgsConstructor
public @Data  class EmailType {

    @NonNull String email;
    @NonNull String titre;
    @NonNull String dateDeFinDuPret;

    public EmailType(String email, String titre, Date dateDeFinDuPret) {
    }
}