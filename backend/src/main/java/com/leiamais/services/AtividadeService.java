package com.leiamais.services;

import com.leiamais.models.Atividade;
import com.leiamais.models.Turma;
import com.leiamais.repositories.AtividadeRepository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;
    private final TurmaService turmaService;

    public AtividadeService(AtividadeRepository atividadeRepository,TurmaService turmaService) {
        this.atividadeRepository = atividadeRepository;
        this.turmaService = turmaService;
    }

    public List<Atividade> listarTodas() {
        return atividadeRepository.findAll();
    }

    @EntityGraph(attributePaths = "respostas")
    public Optional<Atividade> buscarPorId(UUID id) {
        return atividadeRepository.findByIdComRespostas(id);
    }

    public Optional<Atividade> buscarPorNome(String nome) {
        return atividadeRepository.findAll().stream()
                .filter(atividade -> atividade.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public Optional<Atividade> buscarPorLivro(String isbn) {
        return atividadeRepository.findAll().stream()
                .filter(atividade -> atividade.getLivro() != null && atividade.getLivro().getISBN().equals(isbn))
                .findFirst();
    }
    public Optional<Atividade> findByNome(String nome) {
        List<Atividade> atividades = atividadeRepository.findAll();
        for(Atividade a : atividades) {
            if(a.getNome().equals(nome)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    /*public List<Atividade> buscarPorTurma(UUID turmaId) {
        return atividadeRepository.findAll().stream()
                .filter(atividade -> atividade.getTurma() != null && atividade.getTurma().getId().equals(turmaId))
                .toList();
    }*/


    public Atividade salvar(UUID idTurma, Atividade atividade) {

        if (atividade.getEnunciado() != null && atividade.getEnunciado().size() > 3) {
            throw new IllegalArgumentException("Cada atividade pode ter no máximo 3 enunciados.");
        }

        if (atividade.getPrazoEntrega().isBefore(LocalDate.now().minusDays(10))) {
            throw new IllegalArgumentException("O prazo de entrega não pode ser no passado.");
        }
        Atividade atividadeCriada =  atividadeRepository.save(atividade);
        turmaService.adicionarAtividade(idTurma, atividade);
        return atividadeCriada;
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