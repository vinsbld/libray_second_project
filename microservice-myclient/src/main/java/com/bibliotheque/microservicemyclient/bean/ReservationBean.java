package com.bibliotheque.microservicemyclient.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public @Data
class ReservationBean implements Serializable {

    private Long id;

    private Long idUtilisateur;

    private Date dateDeDebutPret;

    private Date dateDeFinDuPret;

    private boolean prolongerPret;

    @JsonProperty("copie")
    private CopieBean copieBean;

    public ReservationBean(Long id, Long idUtilisateur, Date dateDeDebutPret, Date dateDeFinDuPret, boolean prolongerPret, CopieBean copieBean) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.dateDeDebutPret = dateDeDebutPret;
        this.dateDeFinDuPret = dateDeFinDuPret;
        this.prolongerPret = prolongerPret;
        this.copieBean = copieBean;
    }

    @Override
    public String toString() {
        return "ReservationBean{" +
                "id=" + id +
                ", idUtilisateur=" + idUtilisateur +
                ", dateDeDebutPret=" + dateDeDebutPret +
                ", dateDeFinDuPret=" + dateDeFinDuPret +
                ", prolongerPret=" + prolongerPret +
                ", copieBean=" + copieBean +
                '}';
    }
}
