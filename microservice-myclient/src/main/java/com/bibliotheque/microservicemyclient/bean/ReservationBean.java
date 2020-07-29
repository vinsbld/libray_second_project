package com.bibliotheque.microservicemyclient.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ReservationBean {

    private Long id;

    private Long idUtilisateur;

    private Date dateDeReservation;

    @JsonProperty("livre")
    private LivreBean livreBean;

    public ReservationBean(Long id, Long idUtilisateur, Date dateDeReservation, LivreBean livreBean) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.dateDeReservation = dateDeReservation;
        this.livreBean = livreBean;
    }

    @Override
    public String toString() {
        return "ReservationBean{" +
                "id=" + id +
                ", idUtilisateur=" + idUtilisateur +
                ", dateDeReservation=" + dateDeReservation +
                ", livreBean=" + livreBean +
                '}';
    }
}
