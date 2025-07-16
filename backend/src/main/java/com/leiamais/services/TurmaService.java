package com.leiamais.services;

import com.leiamais.models.Aluno;
import com.leiamais.models.Professor;
import com.leiamais.models.Turma;
import com.leiamais.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final ProfessorService professorService;
    private final AlunoService alunoService;


    @Autowired
    public TurmaService(TurmaRepository turmaRepository, ProfessorService professorService, AlunoService alunoService) {
        this.turmaRepository = turmaRepository;
        this.professorService = professorService;
        this.alunoService = alunoService;
    }

    public Turma RegistrarTurma(Turma turma) {
        if(findByNome(turma.getNome()) != null) {
            throw new IllegalArgumentException("Nome ja registrado.");
        }
        return turmaRepository.save(turma);
    }

    public Turma findByNome(String nome) {
        List<Turma> turma = turmaRepository.findAll();
        for(Turma turma1 : turma) {
            if(turma1.getNome().equals(nome)) {
                return turma1;
            }
        }
        return null;
    }

    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }

    public Turma buscarPorId(UUID id) {
        return turmaRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Turma não encontrada com ID: " + id));
    }

    public Turma criarTurma(Turma turma) {
        return turmaRepository.save(turma);
    }

    public Turma atualizarTurma(UUID id, Turma novaTurma) {
        Optional<Turma> optionalTurma = turmaRepository.findById(id);

        if (optionalTurma.isPresent()) {
            Turma turmaExistente = optionalTurma.get();
            turmaExistente.setNome(novaTurma.getNome());
            //turmaExistente.setAlunosMatriculados(novaTurma.getAlunosMatriculados());
            //turmaExistente.setProfessores(novaTurma.getProfessor());
            //turmaExistente.setLivros(novaTurma.getLivros());
            turmaExistente.setAtividades(novaTurma.getAtividades());

            return turmaRepository.save(turmaExistente);
        } else {
            throw new RuntimeException("Turma não encontrada com ID: " + id);
        }
    }

    public void deletarTurma(UUID id) {
        if (!turmaRepository.existsById(id)) {
            throw new RuntimeException("Turma não encontrada com ID: " + id);
        }
        turmaRepository.deleteById(id);
    }

    public Professor adicionarProfessor(String turmaNome,String cpf) {
        Turma turma = findByNome(turmaNome);
        Professor professor = professorService.buscarPorCpf(cpf);
        turma.setProfessores(professor);
        return professor;
    }

    public Aluno adicionarAluno(String turmaNome,String matriculaAluno){
        Turma turma = findByNome(turmaNome);
        Optional<Aluno> aluno = alunoService.buscarPorMatricula(matriculaAluno);
        turma.setAlunosMatriculados(aluno.get());
        return aluno.get();
    }



}
