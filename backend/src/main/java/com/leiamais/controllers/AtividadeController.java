package com.leiamais.controllers;

import com.leiamais.models.Atividade;
import com.leiamais.services.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/atividades")
@CrossOrigin(origins = "http://localhost:5173")
public class AtividadeController {
    private final AtividadeService atividadeService;

    public AtividadeController(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    @GetMapping
    public ResponseEntity<List<Atividade>> listarTodas() {
        return ResponseEntity.ok(atividadeService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atividade> buscarPorId(@PathVariable UUID id) {
        Optional<Atividade> atividadeOptional = atividadeService.buscarPorId(id);
        if (atividadeOptional.isPresent()) {
            return ResponseEntity.ok(atividadeOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/enunciado/{enunciado}")
    public ResponseEntity<Atividade> buscarPorEnunciado(@PathVariable String enunciado) {
        Optional<Atividade> atividadeOptional = atividadeService.buscarPorEnunciado(enunciado);
        if (atividadeOptional.isPresent()) {
            return ResponseEntity.ok(atividadeOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
