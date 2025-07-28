package com.leiamais.services;

import com.leiamais.models.Aluno;
import com.leiamais.models.Turma;
import com.leiamais.repositories.AlunoRepository;
import com.leiamais.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoService {


    private final AlunoRepository alunoRepository;
    TurmaRepository turmaRepository;
    //private final TurmaService turmaService;

    public AlunoService(AlunoRepository alunoRepository, TurmaRepository turmaRepository) {
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
        //this.turmaService = turmaService;
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorId(UUID id) {
        Aluno alunoRetorno = new Aluno();
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            alunoRetorno = aluno.get();
        }

        return alunoRetorno;
    }

    public Optional<Aluno> buscarPorMatricula(String matricula) {
        Aluno alunoRetorno = null;
        for(Aluno aluno : listarTodos()){
            if(aluno.getMatricula().equals(matricula)){
                alunoRetorno = aluno;
            }
        }

        return Optional.ofNullable(alunoRetorno);
    }

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }


    public void deletar(UUID id) {
        alunoRepository.deleteById(id);
    }

    public List<Aluno> buscarAlunosMinhaTurma(UUID idAluno) {
        List<Aluno> alunosRetorno = new ArrayList<>();
        Aluno aluno = buscarPorId(idAluno);

        for(Turma t : turmaRepository.findAll()){
            for(Aluno a : t.getAlunosMatriculados()){
                if(t.getAlunosMatriculados().contains(aluno)){
                    alunosRetorno.add(a);
                }
            }
        }
        return alunosRetorno;
    }

    public Aluno atualizarAluno(UUID id, Aluno novoAluno) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);

        if (optionalAluno.isPresent()) {
            Aluno alunoExistente = optionalAluno.get();
            alunoExistente.setMatricula(novoAluno.getMatricula());
            alunoExistente.setPontos(novoAluno.getPontos());
            alunoExistente.setQtdLivrosLidos(novoAluno.getQtdLivrosLidos());
            alunoExistente.setQtdLivrosFavoritos(novoAluno.getQtdLivrosFavoritos());
            alunoExistente.setNome(novoAluno.getNome());
            alunoExistente.setEmail(novoAluno.getEmail());


            return alunoRepository.save(alunoExistente);
        } else {
            throw new RuntimeException("Aluno não encontrado com ID: " + id);
        }
    }
}
