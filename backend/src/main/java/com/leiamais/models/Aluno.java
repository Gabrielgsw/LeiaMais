package com.leiamais.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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

    public Aluno(String cpf, String nome, String email, String senha, Cargo cargo, Date dataNascimento, String matricula, int pontos, int qtdLivrosLidos, int qtdLivrosFavoritos) {
        super(cpf, nome, email, senha, cargo, dataNascimento);
        this.matricula = matricula;
        this.pontos = pontos;
        this.qtdLivrosLidos = qtdLivrosLidos;
        this.qtdLivrosFavoritos = qtdLivrosFavoritos;
    }

    public UUID getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getQtdLivrosLidos() {
        return qtdLivrosLidos;
    }

    public void setQtdLivrosLidos(int qtdLivrosLidos) {
        this.qtdLivrosLidos = qtdLivrosLidos;
    }

    public int getQtdLivrosFavoritos() {
        return qtdLivrosFavoritos;
    }

    public void setQtdLivrosFavoritos(int qtdLivrosFavoritos) {
        this.qtdLivrosFavoritos = qtdLivrosFavoritos;
    }
}
