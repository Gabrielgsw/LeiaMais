package com.leiamais.controllers;

import com.leiamais.models.Aluno;
import com.leiamais.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/alunos")
@CrossOrigin(origins = "http://localhost:5173")
public class AlunoController {


    private final AlunoService alunoService;


    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/formulario")
    public ResponseEntity<String> receberFormulario(@RequestBody Map<String, String> form) {
        String nome = form.get("nome");
        String email = form.get("email");
        String numero = form.get("numero");
        String mensagem = form.get("mensagem");

        System.out.println("Recebido:");
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Número: " + numero);
        System.out.println("Mensagem: " + mensagem);

        return ResponseEntity.ok("Formulário recebido com sucesso!");
    }


    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable UUID id) {
        Optional<Aluno> alunoOptional = Optional.ofNullable(alunoService.buscarPorId(id));
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
    
    /*@PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable UUID id, @RequestBody Aluno aluno) {
        Optional<Aluno> alunoOptional = alunoService.buscarPorId(id);
        if (alunoOptional.isPresent()) {
            aluno.setId(id);
            Aluno alunoAtualizado = alunoService.salvar(aluno);
            return ResponseEntity.ok(alunoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public Aluno atualizarAluno(@PathVariable UUID id, @RequestBody Aluno alunoAtualizado) {
        return alunoService.atualizarAluno(id, alunoAtualizado);
    }

}
