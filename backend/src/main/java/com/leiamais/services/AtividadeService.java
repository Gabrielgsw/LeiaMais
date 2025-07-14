package com.leiamais.services;

import com.leiamais.models.Atividade;
import com.leiamais.repositories.AtividadeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public Optional<Atividade> buscarPorNome(String nome) {
        return atividadeRepository.findAll().stream()
                .filter(atividade -> atividade.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public List<Atividade> buscarPorTurma(UUID turmaId) {
        return atividadeRepository.findAll().stream()
                .filter(atividade -> atividade.getTurma() != null && atividade.getTurma().getId().equals(turmaId))
                .toList();
    }


    public Atividade salvar(Atividade atividade) {
        if (atividade.getEnunciado() != null && atividade.getEnunciado().size() > 3) {
            throw new IllegalArgumentException("Cada atividade pode ter no máximo 3 enunciados.");
        }

        if (atividade.getPrazoEntrega().isBefore(LocalDate.now().minusDays(10))) {
            throw new IllegalArgumentException("O prazo de entrega não pode ser no passado.");
        }

        return atividadeRepository.save(atividade);
    }

    public void deletar(UUID id) {
        if (!atividadeRepository.existsById(id)) {
            throw new IllegalArgumentException("Atividade com ID " + id + " não encontrada.");
        }
        atividadeRepository.deleteById(id);
    }

    public List<Atividade> buscarPorTrechoDeEnunciado(String termo) {
    return atividadeRepository.findAll().stream()
        .filter(atividade -> atividade.getEnunciado().stream()
            .anyMatch(enunciado -> enunciado.toLowerCase().contains(termo.toLowerCase())))
        .toList();
    }


    

}