package com.bibliotheque.microservicemylibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public @Data
class Livre implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String titre;
    @NonNull
    private String nomAuteur;
    @NonNull
    private String prenomAuteur;
    @NonNull
    private Date dateEdition;

    @NonNull
    @JsonBackReference
    @OneToMany(mappedBy = "livre", fetch = FetchType.EAGER)
    private List<Copie> copies;

}
