package com.bibliotheque.microservicemyusers.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Entity
@RequiredArgsConstructor
public @Data
class Utilisateur implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String pseudo;
    @NonNull
    private String motDePasse;
    @NonNull
    @Email
    private String email;

    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<RoleEnum> roleEnums;

    public Utilisateur() {

        this.roleEnums = Collections.singletonList(RoleEnum.USER);
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", email='" + email + '\'' +
                ", roleEnums=" + roleEnums +
                '}';
    }
}
