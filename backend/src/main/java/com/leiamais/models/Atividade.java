package com.leiamais.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(nullable = false)
    private String enunciado;
    @Column
    private String nome;
    @Column
    private String feedback;
    @OneToMany (mappedBy = "atividade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resposta> respostas;
    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    public UUID getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

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
