package com.bibliotheque.microservicemylibrary.controller;

import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.model.Reservation;


public class ReservationDTO {

    private Livre livre;
    private Reservation reservation;
    private Emprunt emprunt;
    private Integer position;
    private String stateEnum;

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Emprunt getEmprunt() {
        return emprunt;
    }

    public void setEmprunt(Emprunt emprunt) {
        this.emprunt = emprunt;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getStateEnum() {
        return stateEnum;
    }

    public void setStateEnum(String stateEnum) {
        this.stateEnum = stateEnum;
    }
}
