package com.bibliotheque.microservicemyclient.bean;

import java.util.Optional;

public class ReservationBeanDTO {

    private Optional<CopieBean> copie;
    private ReservationBean reservation;
    private EmpruntBean emprunt;
    private Integer position;

    public Optional<CopieBean> getCopie() {
        return copie;
    }

    public void setCopie(Optional<CopieBean> copie) {
        this.copie = copie;
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
}
