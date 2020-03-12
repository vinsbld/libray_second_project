package com.bibliotheque.microservicemyclient.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public @Data
class LivreBean implements Serializable {

    private Long id;

    private String titre;

    private String nomAuteur;

    private String prenomAuteur;

    private Date dateEdition;

    private String editeur;

    @JsonProperty("copie")
    private Set<CopieBean> copieBeans;

    public LivreBean(Long id, String titre, String nomAuteur, String prenomAuteur, Date dateEdition, String editeur, Set<CopieBean> copieBeans) {
        this.id = id;
        this.titre = titre;
        this.nomAuteur = nomAuteur;
        this.prenomAuteur = prenomAuteur;
        this.dateEdition = dateEdition;
        this.editeur = editeur;
        this.copieBeans = copieBeans;
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
                ", copieBeans=" + copieBeans +
                '}';
    }
}
