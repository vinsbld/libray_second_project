package com.bibliotheque.microservicemyclient.bean;

public class CopieBeanDTO {

    private ReservationBean reservation;
    private CopieBean copie;

    public ReservationBean getReservation() {
        return reservation;
    }

    public void setReservation(ReservationBean reservation) {
        this.reservation = reservation;
    }

    public CopieBean getCopie() {
        return copie;
    }

    public void setCopie(CopieBean copie) {
        this.copie = copie;
    }
}
