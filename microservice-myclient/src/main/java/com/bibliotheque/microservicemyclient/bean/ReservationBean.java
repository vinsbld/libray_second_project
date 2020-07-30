package com.bibliotheque.microservicemyclient.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ReservationBean {

    private Long id;

    private Long idUtilisateur;

    private Date dateDeReservation;

    @JsonProperty("copieReservation")
    private CopieBean copieBean;

    public ReservationBean(Long id, Long idUtilisateur, Date dateDeReservation, CopieBean copieBean) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.dateDeReservation = dateDeReservation;
        this.copieBean = copieBean;
    }

    @Override
    public String toString() {
        return "ReservationBean{" +
                "id=" + id +
                ", idUtilisateur=" + idUtilisateur +
                ", dateDeReservation=" + dateDeReservation +
                ", copieBean=" + copieBean +
                '}';
    }
}
