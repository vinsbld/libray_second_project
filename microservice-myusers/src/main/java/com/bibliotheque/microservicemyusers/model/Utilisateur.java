package com.bibliotheque.microservicemyusers.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String pseudo;
    @NotNull
    private String motDePasse;
    @NotNull
    @Email
    private String email;

    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<RoleEnum> roleEnums;

    public Utilisateur() {

        this.roleEnums = Collections.singletonList(RoleEnum.USER);
    }


    public Utilisateur(@NotNull String pseudo, @NotNull String motDePasse, List<RoleEnum> roleEnums, String email) {
        this.pseudo = pseudo;
        setMotDePasse(motDePasse);
        this.roleEnums = roleEnums;
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

    public List<RoleEnum> getRoleEnums() {
        return roleEnums;
    }

    public void setRoleEnums(List<RoleEnum> roleEnums) {
        this.roleEnums = roleEnums;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", roleEnums=" + roleEnums +
                '}';
    }
}
