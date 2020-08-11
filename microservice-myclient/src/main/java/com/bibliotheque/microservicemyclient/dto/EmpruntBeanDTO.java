package com.bibliotheque.microservicemyclient.dto;

import com.bibliotheque.microservicemyclient.bean.CopieBean;
import com.bibliotheque.microservicemyclient.bean.EmpruntBean;
import com.bibliotheque.microservicemyclient.bean.LivreBean;

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
