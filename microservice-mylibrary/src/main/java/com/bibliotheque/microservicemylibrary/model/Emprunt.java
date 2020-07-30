package com.bibliotheque.microservicemylibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
public @Data
class Emprunt {

    @Id
    @GeneratedValue
    private Long id;


    private Long idUtilisateur;

    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date dateDeDebutEmprunt;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateDeFinEmprunt;

    private Date dateRetour;

    private boolean rendu;

    @Transient
    private Date dateDuJour = new Date();

    private boolean prolongerEmprunt;

    @JsonManagedReference
    @ManyToOne
    private Copie copie;

}
