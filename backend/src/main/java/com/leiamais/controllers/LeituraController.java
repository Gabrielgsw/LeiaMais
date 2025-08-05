package com.leiamais.controllers;

import com.leiamais.models.Aluno;
import com.leiamais.models.Leitura;
import com.leiamais.models.Usuario;
import com.leiamais.services.AlunoService;
import com.leiamais.services.LeituraService;
import com.leiamais.services.UsuarioSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/leituras")
public class LeituraController {

    @Autowired
    private final LeituraService leituraService;
    @Autowired
    private AlunoService alunoService;

    public LeituraController(LeituraService leituraService) {
        this.leituraService = leituraService;
    }


    @GetMapping
    public ResponseEntity<List<Leitura>> listarTodasLeituras() {
        return ResponseEntity.ok(leituraService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leitura> listarLeiturarPorId(@PathVariable UUID id) {
        return leituraService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/leitura/{idUser}/{isbn}")
    public ResponseEntity<Leitura> listarLeituraAtual(@PathVariable UUID idUser, @PathVariable String isbn) {
        Optional<Leitura> leitura = leituraService.buscarLeitura(idUser, isbn);
        if(leitura.isPresent()){
            return ResponseEntity.ok(leitura.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{ISBN}")
    public ResponseEntity<Leitura> criar(@PathVariable String ISBN) {
       UUID idUser = UsuarioSession.getInstance().getLoggedInUsuario().getId();
       Optional<Aluno> aluno = Optional.ofNullable(alunoService.buscarPorId(idUser));
       String matriculaAluno = aluno != null ? aluno.get().getMatricula() : null;
        return ResponseEntity.ok(leituraService.criar(idUser,ISBN));
    }



    @PutMapping("/{id}/{status}/{avaliacao}")
    public ResponseEntity<Leitura> atualizarStatus(@PathVariable UUID id, @PathVariable String status,@PathVariable int avaliacao) {
        try {
            return ResponseEntity.ok(leituraService.update(id, status,avaliacao));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        leituraService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
