package com.leiamais.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Importe esta anotação

@Entity
public class Resposta {

    @Id
    @GeneratedValue
    private UUID id;

    @ElementCollection
    @Column(nullable = false)
    private List<String> respostas;

    @Column
    private float nota;

    // --- CORREÇÃO APLICADA ---
    // Adicionada a anotação @JsonIgnoreProperties para evitar erros de serialização
    // quando o objeto Aluno é carregado de forma "preguiçosa" (lazy).
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atividade_id", nullable = false)
    @JsonBackReference
    private Atividade atividade;

    @Column
    private String feedback;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataEntrega;

    @PrePersist
    protected void onCreate() {
        this.dataEntrega = LocalDateTime.now();
    }

    // --- Construtores, Getters e Setters ---
    // (O seu código aqui já estava bom)

    public Resposta() {
    }

    public Resposta(UUID id, List<String> respostas, float nota, Aluno aluno, Atividade atividade, String feedback) {
        this.id = id;
        this.respostas = respostas;
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

    public List<String> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<String> respostas) {
        this.respostas = respostas;
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