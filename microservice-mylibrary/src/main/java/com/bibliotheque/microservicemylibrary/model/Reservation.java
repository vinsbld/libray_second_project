package com.bibliotheque.microservicemylibrary.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    private Long idUtilisateur;

    private Date dateDeReservation;

    @ElementCollection(targetClass = StateEnum.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<StateEnum> stateEnums;

    @JsonManagedReference
    @ManyToOne
    private Copie copie;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", idUtilisateur=" + idUtilisateur +
                ", dateDeReservation=" + dateDeReservation +
                ", stateEnums=" + stateEnums +
                ", copie=" + copie +
                '}';
    }
}
