package com.leiamais.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Atividade {

    @Id
    @GeneratedValue
    private UUID id;
    @ElementCollection
    @Column//(nullable = false)
    private List<String> enunciado;
    @Column
    private String nome;
    @Column//(nullable = false, updatable = false)
    private LocalDate dataCriacao;
    @Column//(nullable = false)
    private LocalDate prazoEntrega;
    @OneToMany (mappedBy = "atividade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resposta> respostas;
    @ManyToOne
    @JoinColumn//(name = "professor_id", nullable = false)
    private Professor professor;
    @ManyToOne
    private Livro livro;
    @ManyToOne
   // @JoinColumn//(name = "turma_id", nullable = false)
    //private Turma turma;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDate.now();
    }


    public Atividade() {
        this.enunciado = new ArrayList<>();
        this.respostas = new ArrayList<>();
        this.prazoEntrega = LocalDate.now();
        this.dataCriacao = LocalDate.now();
    }


    public Atividade(UUID id, List<String> enunciado, String nome, List<Resposta> respostas, Professor professor, Livro livro/*, Turma turma*/, LocalDateTime dataCriacao, LocalDateTime prazoEntrega) {
        this.id = id;
        this.enunciado = (enunciado != null) ? enunciado : new ArrayList<>();
        this.nome = nome;
        this.respostas = (respostas != null) ? respostas : new ArrayList<>();
        this.professor = professor;
        this.livro = livro;
        //this.turma = turma;
        this.dataCriacao = LocalDate.now();
        this.prazoEntrega = LocalDate.now();
    }



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

    public void setEnunciado(List<String> enunciado) {
        this.enunciado = enunciado;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
    public LocalDate getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(LocalDate prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    /*public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }*/

    public void adicionarEnunciado(String enunciado) {
        this.enunciado.add(enunciado);
    }

    public void adicionarEnunciado(List<String> enunciados) {
        this.enunciado.addAll(enunciados);
    }

    public void removerEnunciado(int index) {
        if (index >= 0 && index < enunciado.size()) {
            this.enunciado.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Índice fora dos limites da lista de enunciados.");
        }
    }

    public void removerEnunciado(String enunciado) {
        this.enunciado.remove(enunciado);
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



    public void adicionarResposta(Resposta resposta) {
        this.respostas.add(resposta);
        resposta.setAtividade(this);
    }
}
