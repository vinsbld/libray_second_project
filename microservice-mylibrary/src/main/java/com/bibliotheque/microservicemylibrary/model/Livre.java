package com.bibliotheque.microservicemylibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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


    private Date dateEdition;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private String editeur;

    @JsonBackReference
    @OneToMany(mappedBy = "livre", fetch = FetchType.EAGER)
    private List<Copie> copies;

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

}
