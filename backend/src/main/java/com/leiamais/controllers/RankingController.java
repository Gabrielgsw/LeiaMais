package com.leiamais.controllers;

import com.leiamais.models.Ranking;
import com.leiamais.models.Turma;
import com.leiamais.services.RankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rankings")
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping
    public ResponseEntity<List<Ranking>> listarTodos() {
        return ResponseEntity.ok(rankingService.listarTodos());
    }

    @GetMapping("/{idTurma}")
    public ResponseEntity<List<Ranking>> listarPorTurma(@PathVariable UUID idTurma) {
        return ResponseEntity.ok(Collections.singletonList(rankingService.listarPorTurma(idTurma)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ranking> buscarPorId(@PathVariable UUID id) {
        return rankingService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ranking> salvar(@RequestBody Ranking ranking) {
        return ResponseEntity.ok(rankingService.salvar(ranking));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ranking> atualizar(@PathVariable UUID id, @RequestBody Ranking ranking) {
        try {
            Ranking atualizado = rankingService.atualizar(id, ranking);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        try {
            rankingService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}