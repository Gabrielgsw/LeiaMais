package com.leiamais.services;

import com.leiamais.models.Atividade;
import com.leiamais.repositories.AtividadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;

    public AtividadeService(AtividadeRepository atividadeRepository) {
        this.atividadeRepository = atividadeRepository;
    }

    public List<Atividade> listarTodas() {
        return atividadeRepository.findAll();
    }

    public Optional<Atividade> buscarPorId(UUID id) {
        return atividadeRepository.findById(id);
    }

    public Atividade salvar(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public void deletar(UUID id) {
        atividadeRepository.deleteById(id);
    }

    public Optional<Atividade> buscarPorEnunciado(String enunciado) {
        return atividadeRepository.findByEnunciado(enunciado);
    }
    
    public List<Atividade> buscarPorNota(float nota) {
        return atividadeRepository.findByNota(nota);
    }
}