package com.leiamais.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor // Adicionado para criar um construtor sem argumentos, exigido pelo JPA
public class Atividade {

    @Id
    @GeneratedValue
    private UUID id;

    @ElementCollection
    private List<String> enunciado = new ArrayList<>();

    @Column
    private String nome;
    @Column
    private LocalDate dataCriacao = LocalDate.now();
    @Column
    private LocalDate prazoEntrega = LocalDate.now();

    @OneToMany(mappedBy = "atividade", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Resposta> respostas = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Professor professor;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Livro livro;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDate.now();
    }

    public void adicionarResposta(Resposta resposta) {
        this.respostas.add(resposta);
        resposta.setAtividade(this);
    }
}
