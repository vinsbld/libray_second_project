package com.bibliotheque.microservicemyclient.dto;

import com.bibliotheque.microservicemyclient.bean.CopieBean;
import com.bibliotheque.microservicemyclient.bean.EmpruntBean;
import com.bibliotheque.microservicemyclient.bean.LivreBean;

public class CopieBeanDTO {

    private Long id;
    private Integer isbn;
    private EmpruntBean emprunt;
    private CopieBean copie;
    private LivreBean livre;
    private boolean disponible;

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LivreBean getLivre() {
        return livre;
    }

    public void setLivre(LivreBean livre) {
        this.livre = livre;
    }

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