package com.leiamais.controllers;

import com.leiamais.dtos.RequisicaoRespostaDTO;
import com.leiamais.models.Aluno;
import com.leiamais.models.Atividade;
import com.leiamais.models.Resposta;
import com.leiamais.repositories.RespostaRepository;
import com.leiamais.services.AlunoService;
import com.leiamais.services.AtividadeService;
import com.leiamais.services.RepostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/resposta")
@CrossOrigin(origins = "http://localhost:5173")
public class RespostaController {
    private final AlunoService alunoService;
    private final RepostaService repostaService;
    private final AtividadeService atividadeService;


    public RespostaController(AlunoService alunoService, RepostaService repostaService, AtividadeService atividadeService) {
        this.alunoService = alunoService;
        this.repostaService = repostaService;
        this.atividadeService = atividadeService;

    }

    @GetMapping
    public ResponseEntity<List<Resposta>> listarTodos() {
        return ResponseEntity.ok(repostaService.listarRespostas());
    }

    @PostMapping
    public ResponseEntity<Resposta> salvar(@RequestBody RequisicaoRespostaDTO dto) {
        Optional<Atividade> atv = atividadeService.buscarPorNome(dto.getNomeAtividade());
        Aluno aluno = alunoService.buscarPorId(dto.getAlunoId());
        Resposta resposta = null;
        if (aluno != null) {
            resposta = repostaService.responderAtividade(aluno,dto.getNomeAtividade(), dto.getTexto());
        }

        if (resposta != null) {
            Atividade a = atv.get();
            a.adicionarResposta(resposta);
            return ResponseEntity.ok(resposta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    /*@GetMapping("/{id}")
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
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.salvar(aluno);
        return ResponseEntity.status(201).body(novoAluno);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable UUID id, @RequestBody Aluno aluno) {
        Optional<Aluno> alunoOptional = alunoService.buscarPorId(id);
        if (alunoOptional.isPresent()) {
            aluno.setId(id);
            Aluno alunoAtualizado = alunoService.salvar(aluno);
            return ResponseEntity.ok(alunoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable UUID id) {
        Optional<Aluno> alunoOptional = alunoService.buscarPorId(id);
        if (alunoOptional.isPresent()) {
            alunoService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    /*@GetMapping("/nome/{nome}")
    public ResponseEntity<Aluno> buscarPorNome(@PathVariable String nome) {
        Optional<Aluno> alunoOptional = alunoService.buscarPorNome(nome);
        if (alunoOptional.isPresent()) {
            return ResponseEntity.ok(alunoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<Aluno> buscarPorEmail(@PathVariable String email) {
        Optional<Aluno> alunoOptional = alunoService.buscarPorEmail(email);
        if (alunoOptional.isPresent()) {
            return ResponseEntity.ok(alunoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/


}
