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
public @Data
class Reservation{

    @Id
    @GeneratedValue
    private Long id;


    private Long idUtilisateur;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date dateDeDebutPret;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateDeFinDuPret;

    private Date dateRetour;

    private boolean prolongerPret;

    @JsonManagedReference
    @ManyToOne
    private Copie copie;

}
