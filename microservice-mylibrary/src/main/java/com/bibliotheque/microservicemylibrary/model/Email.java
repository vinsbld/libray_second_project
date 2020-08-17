package com.bibliotheque.microservicemylibrary.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Getter
@Setter
public
class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Column(columnDefinition = "TEXT")
    private String objet;

    @NonNull
    @Column(columnDefinition = "TEXT")
    private String contenu;
}