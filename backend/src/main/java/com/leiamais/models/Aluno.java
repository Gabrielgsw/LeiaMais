package com.leiamais.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("ALUNO")
public class Aluno extends Usuario{

    @Column(unique=true)
    private String matricula;
    @Column
    private int pontos;
    @Column
    private int qtdLivrosLidos;
    @Column
    private int qtdLivrosFavoritos;
    
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Leitura> leituras = new ArrayList<>();

    public Aluno(String cpf, String nome, String email, String senha, Cargo cargo, Date dataNascimento,UUID id, String matricula, int pontos, int qtdLivrosLidos, int qtdLivrosFavoritos) {
        super(cpf, nome, email, senha, cargo, dataNascimento);
        this.matricula = matricula;
        this.pontos = pontos;
        this.qtdLivrosLidos = qtdLivrosLidos;
        this.qtdLivrosFavoritos = qtdLivrosFavoritos;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setQtdLivrosLidos(int qtdLivrosLidos) {
        this.qtdLivrosLidos = qtdLivrosLidos;
    }

    public void setQtdLivrosFavoritos(int qtdLivrosFavoritos) {
        this.qtdLivrosFavoritos = qtdLivrosFavoritos;
    }


}
