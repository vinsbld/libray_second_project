package com.bibliotheque.microservicemylibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

import javax.persistence.*;

import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
public
class Copie {

    @Id
    @GeneratedValue
    private Long id;

    private Integer isbn;

    private boolean disponible;

    @JsonBackReference
    @ManyToOne
    private Livre livre;

    @JsonBackReference
    @OneToMany(mappedBy = "copie", fetch = FetchType.EAGER)
    private List<Emprunt> emprunts;

    @Override
    public String toString() {
        return "Copie{" +
                "id=" + id +
                ", isbn=" + isbn +
                ", disponible=" + disponible +
                ", livre=" + livre +
                ", emprunts=" + emprunts +
                '}';
    }
}
