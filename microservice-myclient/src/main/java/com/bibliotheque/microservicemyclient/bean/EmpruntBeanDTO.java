package com.bibliotheque.microservicemyclient.bean;

public class EmpruntBeanDTO {

    private EmpruntBean emprunt;
    private CopieBean copie;
    private LivreBean livre;

    public EmpruntBean getEmprunt() {
        return emprunt;
    }

    public void setEmprunt(EmpruntBean emprunt) {
        this.emprunt = emprunt;
    }

    public CopieBean getCopie() {
        return copie;
    }

    public void setCopie(CopieBean copie) {
        this.copie = copie;
    }

    public LivreBean getLivre() {
        return livre;
    }

    public void setLivre(LivreBean livre) {
        this.livre = livre;
    }
}
