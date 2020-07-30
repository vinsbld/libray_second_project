package com.bibliotheque.microservicemylibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
public @Data
class Copie {

    @Id
    @GeneratedValue
    private Long id;

    private Integer isbn;

    private boolean disponible;

    @JsonManagedReference
    @ManyToOne
    private Livre livre;

    @JsonBackReference
    @OneToMany(mappedBy = "copie", fetch = FetchType.EAGER)
    private List<Emprunt> emprunts;

    @JsonBackReference
    @OneToMany(mappedBy = "copieReservation")
    private List<Reservation> reservations;

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
