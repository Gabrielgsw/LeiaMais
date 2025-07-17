package com.leiamais.controllers;

import com.leiamais.models.Aluno;
import com.leiamais.models.Professor;
import com.leiamais.models.Turma;
import com.leiamais.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/turmas")
@CrossOrigin(origins = "http://localhost:5173")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    public ResponseEntity<Turma> criarTurma(@RequestBody Turma turma) {
        return ResponseEntity.ok(turmaService.criarTurma(turma));
    }

    @GetMapping
    public ResponseEntity<List<Turma>> listarTurmas() {
        return ResponseEntity.ok(turmaService.listarTurmas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(turmaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizarTurma(@PathVariable UUID id, @RequestBody Turma turma) {
        try {
            return ResponseEntity.ok(turmaService.atualizarTurma(id, turma));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTurma(@PathVariable UUID id) {
        turmaService.deletarTurma(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/aluno/{turma}/{matricula}")
    public ResponseEntity<Aluno> adicionarAluno(@PathVariable String turma,@PathVariable String matricula) {
        System.out.println("Adicionando aluno com matrícula: " + matricula + " à turma: " + turma);
        return ResponseEntity.ok(turmaService.adicionarAluno(turma, matricula));
    }

    @PostMapping("/professor/{turma}/{cpf}")
    public ResponseEntity<Professor> adicionarProfessor(@PathVariable String turma, @PathVariable String cpf) {
        return ResponseEntity.ok(turmaService.adicionarProfessor(turma, cpf));
    }

}
