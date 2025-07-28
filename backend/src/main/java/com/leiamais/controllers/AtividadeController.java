package com.leiamais.controllers;

import com.leiamais.models.Aluno;
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
@RequestMapping("api/atividades")
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

    @GetMapping("/buscarPorNome")
    public ResponseEntity<Atividade> buscarPorNome(@RequestParam String nome) {
        Optional<Atividade> atividadeOptional = atividadeService.buscarPorNome(nome);
        if (atividadeOptional.isPresent()) {
            return ResponseEntity.ok(atividadeOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscarPorTrechoDeEnunciado")
    public ResponseEntity<List<Atividade>> buscarPorTrechoDeEnunciado(@RequestParam String termo) {
        List<Atividade> atividades = atividadeService.buscarPorTrechoDeEnunciado(termo);
        if (atividades.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(atividades);
        }
    }

    @GetMapping("/livro/{isbn}")
    public ResponseEntity<Atividade> buscarPorLivro(@PathVariable String isbn) {
        Optional<Atividade> ativiadOptional = atividadeService.buscarPorLivro(isbn); 
        if(ativiadOptional.isPresent()) {
            return ResponseEntity.ok(ativiadOptional.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    /*@GetMapping("/buscarPorTurma/{turmaId}")
    public ResponseEntity<List<Atividade>> buscarPorTurma(@PathVariable UUID turmaId) {
        List<Atividade> atividadeOptional = atividadeService.buscarPorTurma(turmaId);
        if (atividadeOptional != null) {
            return ResponseEntity.ok(atividadeOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
    
    @PostMapping("/{idTurma}")
    public ResponseEntity<Atividade> criar(@RequestBody Atividade atividade,@PathVariable UUID idTurma) {        
        Atividade salvo = atividadeService.salvar(idTurma,atividade);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        atividadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
