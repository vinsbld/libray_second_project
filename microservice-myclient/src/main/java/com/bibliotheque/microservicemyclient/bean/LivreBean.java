package com.bibliotheque.microservicemyclient.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public
        class LivreBean implements Serializable {

            private Long id;

            private String titre;

            private String nomAuteur;

            private String prenomAuteur;

            private Date dateEdition;

            private String editeur;

            public Integer nbCopies;

            public Integer nbrCopiesDisponibles;

            private Integer  nbReservations;

            private Integer nbReservationsMax;

            private Date dateRetourLaPlusProche;

            @JsonProperty("copies")
            private List<CopieBean> copieBeans;

            @JsonProperty("reservations")
            private List<ReservationBean> reservationBeans;

            public LivreBean(Long id, String titre, String nomAuteur, String prenomAuteur, Date dateEdition, String editeur, Integer nbCopies, Integer nbrCopiesDisponibles, Integer nbReservations, Integer nbReservationsMax, Date dateRetourLaPlusProche, List<CopieBean> copieBeans, List<ReservationBean> reservationBeans) {
                this.id = id;
                this.titre = titre;
                this.nomAuteur = nomAuteur;
                this.prenomAuteur = prenomAuteur;
                this.dateEdition = dateEdition;
                this.editeur = editeur;
                this.nbCopies = nbCopies;
                this.nbrCopiesDisponibles = nbrCopiesDisponibles;
                this.nbReservations = nbReservations;
                this.nbReservationsMax = nbReservationsMax;
                this.dateRetourLaPlusProche = dateRetourLaPlusProche;
                this.copieBeans = copieBeans;
                this.reservationBeans = reservationBeans;
            }

            @Override
            public String toString() {
                return "LivreBean{" +
                        "id=" + id +
                        ", titre='" + titre + '\'' +
                        ", nomAuteur='" + nomAuteur + '\'' +
                        ", prenomAuteur='" + prenomAuteur + '\'' +
                        ", dateEdition=" + dateEdition +
                        ", editeur='" + editeur + '\'' +
                        ", nbCopies=" + nbCopies +
                        ", nbrCopiesDisponibles=" + nbrCopiesDisponibles +
                        ", nbReservations=" + nbReservations +
                        ", nbReservationsMax=" + nbReservationsMax +
                        ", dateRetourLaPlusProche=" + dateRetourLaPlusProche +
                        ", copieBeans=" + copieBeans +
                        ", reservationBeans=" + reservationBeans +
                        '}';
            }
}
