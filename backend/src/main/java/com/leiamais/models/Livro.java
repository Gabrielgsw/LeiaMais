package com.leiamais.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false,unique = true)
    private String ISBN;
    @Column(nullable = false)
    private String autor;
    @Column(nullable = false)
    private String editora;


}
