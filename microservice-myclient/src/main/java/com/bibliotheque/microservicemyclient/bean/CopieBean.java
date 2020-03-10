package com.bibliotheque.microservicemyclient.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public @Data
class CopieBean implements Serializable {

    private Long id;

    private Integer nbCopies;

    private LivreBean livreBean;

    private List<ReservationBean>reservationBeans;

    public CopieBean(Long id, Integer nbCopies, LivreBean livreBean, List<ReservationBean> reservationBeans) {
        this.id = id;
        this.nbCopies = nbCopies;
        this.livreBean = livreBean;
        this.reservationBeans = reservationBeans;
    }

    @Override
    public String toString() {
        return "CopieBean{" +
                "id=" + id +
                ", nbCopies=" + nbCopies +
                ", livreBean=" + livreBean +
                ", reservationBeans=" + reservationBeans +
                '}';
    }
}
