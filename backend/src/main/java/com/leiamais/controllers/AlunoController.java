package com.leiamais.controllers;

import com.leiamais.models.Aluno;
import com.leiamais.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/alunos")
public class AlunoController {


    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable UUID id) {
        Optional<Aluno> alunoOptional = alunoService.buscarPorId(id);
        if (alunoOptional.isPresent()) {
            return ResponseEntity.ok(alunoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Aluno> buscarPorMatricula(@PathVariable String matricula) {
        Optional<Aluno> alunoOptional = alunoService.buscarPorMatricula(matricula);
        if (alunoOptional.isPresent()) {
            return ResponseEntity.ok(alunoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody Aluno aluno) {
        Aluno salvo = alunoService.salvar(aluno);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
