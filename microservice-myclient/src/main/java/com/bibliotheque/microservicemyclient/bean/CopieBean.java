package com.bibliotheque.microservicemyclient.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public @Data
class CopieBean implements Serializable {

    private Long id;

    private Integer isbn;

    private boolean disponible;

    @JsonProperty("livre")
    private LivreBean livreBean;

    @JsonProperty("reservation")
    private List<ReservationBean> reservationBeans;

    public CopieBean(Long id, Integer isbn, boolean disponible, LivreBean livreBean, List<ReservationBean> reservationBeans) {
        this.id = id;
        this.isbn = isbn;
        this.disponible = disponible;
        this.livreBean = livreBean;
        this.reservationBeans = reservationBeans;
    }

    @Override
    public String toString() {
        return "CopieBean{" +
                "id=" + id +
                ", isbn=" + isbn +
                ", disponible=" + disponible +
                ", livreBean=" + livreBean +
                ", reservationBeans=" + reservationBeans +
                '}';
    }
}
