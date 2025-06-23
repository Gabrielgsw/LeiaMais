package com.leiamais.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Turma {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String nome;

    @Column()
    @OneToMany
    List<Aluno> alunosMatriculados;

    @Column()
    @OneToMany
    List<Professor> professores;

    @Column()
    @OneToMany
    List<Livro> livros;

    @Column
    @OneToMany
    List<Atividade> atividades;

    public Turma(String nome) {
        this.nome = nome;
        this.alunosMatriculados = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.livros = new ArrayList<>();
        this.atividades = new ArrayList<>();

    }

    public UUID getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
