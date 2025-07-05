package com.leiamais.controllers;

import com.leiamais.models.Atividade;
import com.leiamais.models.Professor;
import com.leiamais.models.Usuario;
import com.leiamais.services.ProfessorService;
import com.leiamais.services.UsuarioService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/professor")
@CrossOrigin(origins = "http://localhost:5173")
public class ProfessorController {

    private final ProfessorService professorService;
    private final UsuarioService usuarioService;

    public ProfessorController(ProfessorService professorService, @Qualifier("usuarioService") UsuarioService usuarioService) {
        this.professorService = professorService;
        this.usuarioService = usuarioService;
    }


    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(professorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable UUID id) {
        Optional<Usuario> user = usuarioService.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Professor usuario) {
        Usuario salvo = professorService.salvar(usuario);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        professorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
