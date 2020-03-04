package com.bibliotheque.microservicemyclient.bean;

import java.util.Date;

public class ReservationBean {

    private Long id;

    private Long idUtilisateur;

    private Date dateDeDebutPret;

    private Date dateDeFinDuPret;

    private boolean prolongerPret;

    public ReservationBean() {
    }

    public ReservationBean(Long id, Long idUtilisateur, Date dateDeDebutPret, Date dateDeFinDuPret, boolean prolongerPret) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.dateDeDebutPret = dateDeDebutPret;
        this.dateDeFinDuPret = dateDeFinDuPret;
        this.prolongerPret = prolongerPret;
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

    @Override
    public String toString() {
        return "ReservationBean{" +
                "id=" + id +
                ", idUtilisateur=" + idUtilisateur +
                ", dateDeDebutPret=" + dateDeDebutPret +
                ", dateDeFinDuPret=" + dateDeFinDuPret +
                ", prolongerPret=" + prolongerPret +
                '}';
    }
}
