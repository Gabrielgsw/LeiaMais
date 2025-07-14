package com.leiamais.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Turma {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @OneToMany
    private List<Aluno> alunosMatriculados = new ArrayList<>();

    @ManyToMany
    private List<Professor> professores = new ArrayList<>();

    @ManyToMany
    private List<Livro> livros = new ArrayList<>();

    @ManyToMany
    private List<Atividade> atividades = new ArrayList<>();


    public Turma() {}

    public Turma(String nome) {
        this.nome = nome;
        this.alunosMatriculados = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.livros = new ArrayList<>();
        this.atividades = new ArrayList<>();
    }

    public Turma(UUID id, String nome, List<Aluno> alunosMatriculados, List<Professor> professores, List<Livro> livros, List<Atividade> atividades) {
        this.id = id;
        this.nome = nome;
        this.alunosMatriculados = alunosMatriculados;
        this.professores = professores;
        this.livros = livros;
        this.atividades = atividades;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public void setAlunosMatriculados(List<Aluno> alunosMatriculados) {
        this.alunosMatriculados = alunosMatriculados;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }
}
