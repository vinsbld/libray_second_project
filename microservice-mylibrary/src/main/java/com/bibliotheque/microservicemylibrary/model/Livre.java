package com.bibliotheque.microservicemylibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
public @Data
class Livre {

    @Id
    @GeneratedValue
    private Long id;


    private String titre;


    private String nomAuteur;


    private String prenomAuteur;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateEdition;

    private String editeur;

    @JsonManagedReference
    @OneToMany(mappedBy = "livre", fetch = FetchType.EAGER)
    private List<Copie> copies;

    @JsonBackReference
    @OneToMany(mappedBy = "livre")
    private List<Reservation> reservations;


    @Transient
    public Integer getNbrCopiesDisponibles(){
       Integer nbDispo = 0;
        for (Copie c : copies) {
            if (c.isDisponible()){
                nbDispo++;
            }
        }
        return nbDispo;
    }

    @Transient
    public Integer getNbCopies(){
        return copies.size();
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", nomAuteur='" + nomAuteur + '\'' +
                ", prenomAuteur='" + prenomAuteur + '\'' +
                ", dateEdition=" + dateEdition +
                ", editeur='" + editeur + '\'' +
                ", copies=" + copies +
                ", reservations=" + reservations +
                '}';
    }
}
