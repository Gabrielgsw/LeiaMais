package com.leiamais.services;

import com.leiamais.models.Aluno;
import com.leiamais.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RepostaService {
    private final AlunoRepository alunoRepository;

    public RepostaService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(UUID id) {
        return alunoRepository.findById(id);
    }

    public Optional<Aluno> buscarPorMatricula(String matricula) {
        return alunoRepository.findByMatricula(matricula);
    }

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public void deletar(UUID id) {
        alunoRepository.deleteById(id);
    }

    public Optional<Aluno> buscarPorNome(String nome) {
        return alunoRepository.findByNome(nome);
    }

    public Optional<Aluno> buscarPorEmail(String email) {
        return alunoRepository.findByEmail(email);
    }
}
