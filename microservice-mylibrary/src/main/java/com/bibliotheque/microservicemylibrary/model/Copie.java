package com.bibliotheque.microservicemylibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public @Data
class Copie implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private Integer isbn;

    @NonNull
    private boolean disponible;

    @NonNull
    @JsonManagedReference
    @ManyToOne
    private Livre livre;

    @NonNull
    @JsonBackReference
    @OneToMany(mappedBy = "copie", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    @Override
    public String toString() {
        return "Copie{" +
                "id=" + id +
                ", isbn=" + isbn +
                ", disponible=" + disponible +
                ", livre=" + livre +
                ", reservations=" + reservations +
                '}';
    }
}
