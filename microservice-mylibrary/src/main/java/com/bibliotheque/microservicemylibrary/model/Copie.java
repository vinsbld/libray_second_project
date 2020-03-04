package com.bibliotheque.microservicemylibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Copie {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Integer nbCopies;


    @JsonBackReference
    @ManyToOne
    @JoinColumn
    private Livre livre;

    @JsonManagedReference
    @OneToMany(mappedBy = "copie", fetch = FetchType.EAGER)
    private Collection<Reservation>reservations;

    public Copie() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Collection<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Integer getNbCopies() {
        return nbCopies;
    }

    public void setNbCopies(Integer nbCopies) {
        this.nbCopies = nbCopies;
    }

    @Override
    public String toString() {
        return "Copie{" +
                "id=" + id +
                ", nbCopies=" + nbCopies +
                ", livre=" + livre +
                ", reservations=" + reservations +
                '}';
    }
}
