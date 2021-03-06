package com.bibliotheque.microservicemylibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public
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


    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", idUtilisateur=" + idUtilisateur +
                ", dateDeDebutEmprunt=" + dateDeDebutEmprunt +
                ", dateDeFinEmprunt=" + dateDeFinEmprunt +
                ", dateRetour=" + dateRetour +
                ", rendu=" + rendu +
                ", dateDuJour=" + dateDuJour +
                ", prolongerEmprunt=" + prolongerEmprunt +
                ", copie=" + copie +
                '}';
    }
}
