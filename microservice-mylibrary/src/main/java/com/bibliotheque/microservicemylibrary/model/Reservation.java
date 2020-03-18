package com.bibliotheque.microservicemylibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    private Date dateDeFinDuPret;
    @NonNull
    private boolean prolongerPret;

    @NonNull
    @JsonManagedReference
    @ManyToOne
    private Copie copie;

}
