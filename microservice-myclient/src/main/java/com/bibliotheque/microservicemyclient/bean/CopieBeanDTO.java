package com.bibliotheque.microservicemyclient.bean;

public class CopieBeanDTO {

    private EmpruntBean emprunt;
    private CopieBean copie;

    public EmpruntBean getEmprunt() {
        return emprunt;
    }

    public void setEmprunt(EmpruntBean empruntBean) {
        this.emprunt = empruntBean;
    }

    public CopieBean getCopie() {
        return copie;
    }

    public void setCopie(CopieBean copie) {
        this.copie = copie;
    }
}
