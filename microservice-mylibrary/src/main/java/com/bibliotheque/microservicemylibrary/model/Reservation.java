package com.bibliotheque.microservicemylibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public @Data
class Reservation implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private Long idUtilisateur;
    @NonNull
    private Date dateDeDebutPret;
    @NonNull
    private Date dateDeFinDuPret;
    @NonNull
    private boolean prolongerPret;

    @NonNull
    @JsonManagedReference
    @ManyToOne
    private Copie copie;

}
