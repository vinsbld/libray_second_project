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
