package com.leiamais.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter // Lombok irá gerar todos os getters
@Setter // Lombok irá gerar todos os setters
@AllArgsConstructor
@NoArgsConstructor
public class Leitura {

    @Id
    @GeneratedValue
    private UUID id;

    // Adicionada a anotação @JsonIgnoreProperties para evitar erros de serialização
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Livro livro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Aluno aluno;

    @Enumerated(EnumType.STRING) // Boa prática para guardar a string da enum em vez do número
    private StatusLivro status;

    @Column(nullable = false)
    private boolean pontoEmLeituraConcedido = false;

    @Column(nullable = false)
    private boolean pontoConcluidoConcedido = false;

    @Column
    private int avaliacao;

}
