package com.bibliotheque.microservicemyclient.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@NoArgsConstructor
@Getter
@Setter
@Data
public class ReservationBean implements Serializable {

    private Long id;

    private Long idUtilisateur;

    private Date dateDeReservation;

    private Integer position;

    private String stateEnum;

    @JsonProperty("livre")
    private LivreBean livreBean;

    public ReservationBean(Long id, Long idUtilisateur, Date dateDeReservation, Integer position, String stateEnum, LivreBean livreBean) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.dateDeReservation = dateDeReservation;
        this.position = position;
        this.stateEnum = stateEnum;
        this.livreBean = livreBean;
    }

    @Override
    public String toString() {
        return "ReservationBean{" +
                "id=" + id +
                ", idUtilisateur=" + idUtilisateur +
                ", dateDeReservation=" + dateDeReservation +
                ", position=" + position +
                ", stateEnum='" + stateEnum + '\'' +
                ", livreBean=" + livreBean +
                '}';
    }
}
