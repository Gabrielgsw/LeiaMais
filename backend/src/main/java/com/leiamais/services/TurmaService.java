package com.leiamais.services;

import com.leiamais.models.Cargo;
import com.leiamais.models.Turma;
import com.leiamais.models.Usuario;
import com.leiamais.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;


    @Autowired
    public TurmaService(TurmaRepository turmaRepository) {this.turmaRepository = turmaRepository;}

    public Turma RegistrarTurma(Turma turma) {
        if(findByNome(turma.getNome()).isPresent()) {
            throw new IllegalArgumentException("Nome ja registrado.");
        }
        return turmaRepository.save(turma);
    }

    public Optional<Turma> findByNome(String nome) {
        List<Turma> turma = turmaRepository.findAll();
        return turma.stream().filter(turma1 -> turma1.getNome().equals(nome)).findFirst();
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
            turmaExistente.setAlunosMatriculados(novaTurma.getAlunosMatriculados());
            turmaExistente.setProfessores(novaTurma.getProfessores());
            turmaExistente.setLivros(novaTurma.getLivros());
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

}
