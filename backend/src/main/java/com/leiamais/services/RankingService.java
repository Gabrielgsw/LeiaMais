package com.leiamais.services;

import com.leiamais.models.Ranking;
import com.leiamais.models.Turma;
import com.leiamais.repositories.RankingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RankingService {

    private final RankingRepository rankingRepository;
    private final TurmaService turmaService;

    public RankingService(RankingRepository rankingRepository,TurmaService turmaService) {
        this.rankingRepository = rankingRepository;
        this.turmaService = turmaService;
    }

    public List<Ranking> listarTodos() {
        return rankingRepository.findAll();
    }

    public Optional<Ranking> buscarPorId(UUID id) {
        return rankingRepository.findById(id);
    }

    public Ranking salvar(Ranking ranking) {
        return rankingRepository.save(ranking);
    }

    public Ranking atualizar(UUID id, Ranking novoRanking) {
        Optional<Ranking> rankingExistente = rankingRepository.findById(id);
        if (rankingExistente.isEmpty()) {
            throw new RuntimeException("Ranking não encontrado com ID: " + id);
        }

        Ranking ranking = rankingExistente.get();
        ranking.setTurma(novoRanking.getTurma());

        return rankingRepository.save(ranking);
    }

    public Ranking buscarRankingPorTurma(UUID idTurma) {
        Ranking ranking = null;
        for(Ranking r : rankingRepository.findAll()){
            if(r.getTurma().getId().equals(idTurma)){
                ranking = r;
            }
        }
        return ranking;
    }

    public Ranking listarPorTurma(UUID idturma) {
        Ranking ranking = buscarRankingPorTurma(idturma);
        Turma turma = turmaService.buscarPorId(idturma);
        ranking.setTurma(turma);
        return ranking;
    }

    public void deletar(UUID id) {
        if (!rankingRepository.existsById(id)) {
            throw new RuntimeException("Ranking com ID " + id + " não encontrado.");
        }
        rankingRepository.deleteById(id);
    }
}
