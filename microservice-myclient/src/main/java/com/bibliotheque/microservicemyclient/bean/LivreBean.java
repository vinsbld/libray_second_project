package com.bibliotheque.microservicemyclient.bean;

import java.util.Date;
import java.util.List;

public class LivreBean {

    private Long id;

    private String titre;

    private String nomAuteur;

    private String prenomAuteur;

    private Date dateEdition;

    private List<CopieBean> copieBeans;

    public LivreBean() {
    }

    public LivreBean(Long id, String titre, String nomAuteur, String prenomAuteur, Date dateEdition, List<CopieBean> copieBeans) {
        this.id = id;
        this.titre = titre;
        this.nomAuteur = nomAuteur;
        this.prenomAuteur = prenomAuteur;
        this.dateEdition = dateEdition;
        this.copieBeans = copieBeans;
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

    public List<CopieBean> getCopieBeans() {
        return copieBeans;
    }

    public void setCopieBeans(List<CopieBean> copieBeans) {
        this.copieBeans = copieBeans;
    }

    @Override
    public String toString() {
        return "LivreBean{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", nomAuteur='" + nomAuteur + '\'' +
                ", prenomAuteur='" + prenomAuteur + '\'' +
                ", dateEdition=" + dateEdition +
                ", copieBeans=" + copieBeans +
                '}';
    }
}
