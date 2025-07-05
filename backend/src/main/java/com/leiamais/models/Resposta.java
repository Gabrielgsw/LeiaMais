package com.leiamais.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Resposta {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String texto;

    @Column
    private float nota;

    @OneToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @OneToOne
    @JoinColumn(name = "atividade_id", nullable = false)
    private Atividade atividade;

    @Column
    private String feedback;


    public Resposta() {
    }


    public Resposta(UUID id, String texto, float nota, Aluno aluno, Atividade atividade, String feedback) {
        this.id = id;
        this.texto = texto;
        this.nota = nota;
        this.aluno = aluno;
        this.atividade = atividade;
        this.feedback = feedback;
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
