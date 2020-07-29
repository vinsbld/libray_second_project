package com.bibliotheque.microservicemylibrary.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

}
