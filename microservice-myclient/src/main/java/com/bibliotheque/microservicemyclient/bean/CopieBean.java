package com.bibliotheque.microservicemyclient.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public @Data
class CopieBean implements Serializable {

    private Long id;

    private Integer isbn;

    @JsonProperty("livre")
    private LivreBean livreBean;

    @JsonProperty("reservation")
    private Set<ReservationBean> reservationBeans;

    public CopieBean(Long id, Integer isbn, LivreBean livreBean, Set<ReservationBean> reservationBeans) {
        this.id = id;
        this.isbn = isbn;
        this.livreBean = livreBean;
        this.reservationBeans = reservationBeans;
    }

    @Override
    public String toString() {
        return "CopieBean{" +
                "id=" + id +
                ", isbn=" + isbn +
                ", livreBean=" + livreBean +
                ", reservationBeans=" + reservationBeans +
                '}';
    }
}
