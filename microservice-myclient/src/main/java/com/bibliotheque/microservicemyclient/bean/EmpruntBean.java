package com.bibliotheque.microservicemyclient.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public @Data
class EmpruntBean implements Serializable {

    private Long id;

    private Long idUtilisateur;

    private Date dateDeDebutEmprunt;

    private Date dateDeFinEmprunt;

    private Date dateRetour;

    private boolean rendu;

    private Date dateDuJour = new Date();

    private boolean prolongerEmprunt;

    @JsonProperty("copie")
    private CopieBean copieBean;

    public EmpruntBean(Long id, Long idUtilisateur, Date dateDeDebutEmprunt, Date dateDeFinEmprunt, boolean prolongerEmprunt, CopieBean copieBean) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.dateDeDebutEmprunt = dateDeDebutEmprunt;
        this.dateDeFinEmprunt = dateDeFinEmprunt;
        this.prolongerEmprunt = prolongerEmprunt;
        this.copieBean = copieBean;
    }

    @Override
    public String toString() {
        return "EmpruntBean{" +
                "id=" + id +
                ", idUtilisateur=" + idUtilisateur +
                ", dateDeDebutPret=" + dateDeDebutEmprunt +
                ", dateDeFinDuPret=" + dateDeFinEmprunt +
                ", prolongerPret=" + prolongerEmprunt +
                ", copieBean=" + copieBean +
                '}';
    }
}
