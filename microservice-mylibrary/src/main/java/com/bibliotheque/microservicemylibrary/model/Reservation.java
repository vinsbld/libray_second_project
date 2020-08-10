package com.bibliotheque.microservicemylibrary.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    private Long idUtilisateur;

    private Date dateDeReservation;

    private Integer position;

    private boolean emailEnvoyer = false;

    private Date dateEnvoiEmail;


    @Enumerated(EnumType.STRING)
    private StateEnum stateEnums;

    @JsonManagedReference
    @ManyToOne
    private Livre livre;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", idUtilisateur=" + idUtilisateur +
                ", dateDeReservation=" + dateDeReservation +
                ", position=" + position +
                ", emailEnvoyer=" + emailEnvoyer +
                ", dateEnvoiEmail=" + dateEnvoiEmail +
                ", stateEnums=" + stateEnums +
                ", livre=" + livre +
                '}';
    }
}
