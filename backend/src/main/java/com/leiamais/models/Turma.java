package com.leiamais.models;

import com.leiamais.repositories.AlunoRepository;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.leiamais.models.Aluno;


@Entity
public class Turma {

    @Id
    @GeneratedValue

    private UUID id;
// nullable = false
    @Column
    private String nome;

    @OneToMany
    private List<Aluno> alunosMatriculados = new ArrayList<>();

    @ManyToMany
    private List<Professor> professores = new ArrayList<>();

    @ManyToMany
    private List<Livro> livros = new ArrayList<>();

    @ManyToMany
    private List<Atividade> atividades = new ArrayList<>();


    public Turma() {
        this.alunosMatriculados = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.livros = new ArrayList<>();
        this.atividades = new ArrayList<>();
    }

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
        System.out.println("[TURMA] Obtendo alunos matriculados na turma: " + this.nome + " - Total de alunos: " + this.alunosMatriculados.size());
        for (Aluno aluno : this.alunosMatriculados) {
            System.out.println("[TURMA] Aluno na turma: " + aluno.getNome());
        }

        return alunosMatriculados;
    }

    public void setAlunosMatriculados(Aluno alunosMatriculado) {
        System.out.println("Aluno --> "+alunosMatriculado.getNome());
        System.out.println("[TURMA] Adicionando aluno: " + alunosMatriculado.getNome() + " à turma: " + this.nome);
        this.alunosMatriculados.add(alunosMatriculado);
        System.out.println("Alunos matriculados -->" + alunosMatriculados.toString());
        System.out.println("[TURMA] Aluno adicionado com sucesso. Total de alunos na turma: " + this.alunosMatriculados.size());
        for (Aluno aluno : this.alunosMatriculados) {
            System.out.println("[TURMA] Aluno na turma: " + aluno.getNome());
        }
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(Professor professor) {
        this.professores.add(professor);
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(Livro livro) {
        this.livros.add(livro);
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(Atividade atividade) {
        this.atividades.add(atividade);
    }
}
