package com.bibliotheque.microservicemyclient.bean;

import java.util.Optional;

public class ReservationBeanDTO {

    private Optional<LivreBean> livre;
    private ReservationBean reservation;
    private EmpruntBean emprunt;
    private Integer position;
    private String stateEnum;

    public Optional<LivreBean> getLivre() {
        return livre;
    }

    public void setLivre(Optional<LivreBean> livre) {
        this.livre = livre;
    }

    public ReservationBean getReservation() {
        return reservation;
    }

    public void setReservation(ReservationBean reservation) {
        this.reservation = reservation;
    }

    public EmpruntBean getEmprunt() {
        return emprunt;
    }

    public void setEmprunt(EmpruntBean emprunt) {
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
