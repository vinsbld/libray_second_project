package com.bibliotheque.microservicemylibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateEdition;
    @NonNull
    private String editeur;

    @NonNull
    @JsonBackReference
    @OneToMany(mappedBy = "livre", fetch = FetchType.EAGER)
    private Set<Copie> copies;

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
                '}';
    }
}
