package com.bibliotheque.microservicemyclient.bean;


public class UtilisateurBean {

    private Long id;

    private String pseudo;

    private String motDePasse;

    private String email;

    public UtilisateurBean() {
    }

    public UtilisateurBean(Long id, String pseudo, String motDePasse, String email) {
        this.id = id;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UtilisateurBean{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
