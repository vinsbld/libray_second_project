package com.bibliotheque.microservicemylibrary.outils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Data
public class EmailTypeReservation {

    @NonNull String email;
    @NonNull String titre;
    @NonNull String deadLine;

    public EmailTypeReservation(@NonNull String email, @NonNull String titre, @NonNull String deadLine) {
        this.email = email;
        this.titre = titre;
        this.deadLine = deadLine;
    }
}
