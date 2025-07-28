package com.leiamais.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @OneToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    // --- CORREÇÃO APLICADA ---
    // Em vez de 'private UUID atividadeId;', usamos a referência ao objeto completo.
    // A anotação @ManyToOne define que muitas Respostas pertencem a uma Atividade.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atividade_id", nullable = false)
    @JsonBackReference // Evita loops infinitos ao converter para JSON
    private Atividade atividade;

    @Column
    private String feedback;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataEntrega;

    @PrePersist
    protected void onCreate() {
        this.dataEntrega = LocalDateTime.now();
    }

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

    public void adicionarResposta(String resposta) {
        this.respostas.add(resposta);
    }

    public void adicionarRespostas(List<String> respostas) {
        this.respostas.addAll(respostas);
    }

    public void removerResposta(int index) {
        if (index >= 0 && index < respostas.size()) {
            this.respostas.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Índice fora dos limites da lista de respostas.");
        }
    }

    public void removerResposta(String resposta) {
        this.respostas.remove(resposta);
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
