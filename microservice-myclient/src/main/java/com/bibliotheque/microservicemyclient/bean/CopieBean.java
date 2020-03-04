package com.bibliotheque.microservicemyclient.bean;

public class CopieBean {

    private Long id;

    private Integer nbCopies;

    public CopieBean() {
    }

    public CopieBean(Long id, Integer nbCopies) {
        this.id = id;
        this.nbCopies = nbCopies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNbCopies() {
        return nbCopies;
    }

    public void setNbCopies(Integer nbCopies) {
        this.nbCopies = nbCopies;
    }

    @Override
    public String toString() {
        return "CopieBean{" +
                "id=" + id +
                ", nbCopies=" + nbCopies +
                '}';
    }
}
