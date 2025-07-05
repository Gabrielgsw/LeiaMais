package com.leiamais.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Atividade {

    @Id
    @GeneratedValue
    private UUID id;
    @ElementCollection
    @Column(nullable = false)
    private List<String> enunciado;
    @Column
    private String nome;
    @OneToMany (mappedBy = "atividade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resposta> respostas;
    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;
    @ManyToOne
    private Livro livro;
    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    public UUID getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    public List<String> getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = Collections.singletonList(enunciado);
    }

    public Livro getLivro() {
        return livro;   }

    public void setLivro(Livro livro) {
        this.livro = livro;     }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    public Professor getProfessor() {
        return professor;
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

}
