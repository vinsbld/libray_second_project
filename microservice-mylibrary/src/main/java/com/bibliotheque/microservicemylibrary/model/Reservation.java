package com.bibliotheque.microservicemylibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    private Long idUtilisateur;
    @NotNull
    private Date dateDeDebutPret;
    @NotNull
    private Date dateDeFinDuPret;
    @NotNull
    private boolean prolongerPret;

    @JsonBackReference
    @ManyToOne
    @JoinColumn
    private Copie copie;

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Date getDateDeDebutPret() {
        return dateDeDebutPret;
    }

    public void setDateDeDebutPret(Date dateDeDebutPret) {
        this.dateDeDebutPret = dateDeDebutPret;
    }

    public Date getDateDeFinDuPret() {
        return dateDeFinDuPret;
    }

    public void setDateDeFinDuPret(Date dateDeFinDuPret) {
        this.dateDeFinDuPret = dateDeFinDuPret;
    }

    public boolean isProlongerPret() {
        return prolongerPret;
    }

    public void setProlongerPret(boolean prolongerPret) {
        this.prolongerPret = prolongerPret;
    }

    public Copie getCopie() {
        return copie;
    }

    public void setCopie(Copie copie) {
        this.copie = copie;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", idUtilisateur=" + idUtilisateur +
                ", dateDeDebutPret=" + dateDeDebutPret +
                ", dateDeFinDuPret=" + dateDeFinDuPret +
                ", prolongerPret=" + prolongerPret +
                ", copie=" + copie +
                '}';
    }
}
