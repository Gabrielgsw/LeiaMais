package com.leiamais.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Aluno extends Usuario{

    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique=true)
    private String matricula;
    @Column
    private int pontos;
    @Column
    private int qtdLivrosLidos;
    @Column
    private int qtdLivrosFavoritos;
}
