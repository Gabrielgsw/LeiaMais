package com.leiamais.services;

import com.leiamais.models.Cargo;
import com.leiamais.models.Turma;
import com.leiamais.models.Usuario;
import com.leiamais.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

}
