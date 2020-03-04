package com.bibliotheque.microservicemylibrary.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
public class Livre {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String titre;
    @NotNull
    private String nomAuteur;
    @NotNull
    private String prenomAuteur;
    private Date dateEdition;

    @JsonManagedReference
    @OneToMany(mappedBy = "livre", fetch = FetchType.EAGER)
    private Collection<Copie>copies;

    public Livre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public String getPrenomAuteur() {
        return prenomAuteur;
    }

    public void setPrenomAuteur(String prenomAuteur) {
        this.prenomAuteur = prenomAuteur;
    }

    public Date getDateEdition() {
        return dateEdition;
    }

    public void setDateEdition(Date dateEdition) {
        this.dateEdition = dateEdition;
    }

    public Collection<Copie> getCopies() {
        return copies;
    }

    public void setCopies(Collection<Copie> copies) {
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", nomAuteur='" + nomAuteur + '\'' +
                ", prenomAuteur='" + prenomAuteur + '\'' +
                ", dateEdition=" + dateEdition +
                ", copies=" + copies +
                '}';
    }
}
