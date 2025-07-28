package com.leiamais.controllers;

import com.leiamais.dtos.CorrecaoRespostaDTO;
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
@RequestMapping("/respostas")
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
        System.out.println("id da atividade: " + dto.getAtividade().getId());
        Optional<Atividade> atv = atividadeService.buscarPorId(dto.getAtividade().getId());
        Aluno aluno = alunoService.buscarPorId(dto.getAlunoId());
        aluno.setPontos(aluno.getPontos() + 2);
        Resposta resposta = null;
        if (aluno != null) {
            resposta = repostaService.responderAtividade(dto);
        }

        if (resposta != null) {
            Atividade a = atv.get();
            a.adicionarResposta(resposta);
            return ResponseEntity.ok(resposta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<Resposta> buscarPorId(@PathVariable UUID id) {
        Optional<Resposta> resposta = repostaService.buscarRespostaPorId(id);
        if (resposta.isPresent()) {
            return ResponseEntity.ok(resposta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        repostaService.excluirResposta(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Resposta> atualizar(@PathVariable UUID id, @RequestBody Resposta resposta) {
        Optional<Resposta> respostaExistente = repostaService.buscarRespostaPorId(id);
        if (respostaExistente.isPresent()) {
            resposta.setId(id);
            repostaService.atualizarResposta(resposta);
            return ResponseEntity.ok(resposta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/atividade/{nome}")
    public ResponseEntity<Map<String, Object>> buscarPorNomeAtividade(@PathVariable String nome) {
        Optional<Atividade> atividade = atividadeService.buscarPorNome(nome);
        if (atividade.isPresent()) {
            List<Resposta> respostas = atividade.get().getRespostas();
            return ResponseEntity.ok(Map.of("atividade", atividade.get(), "respostas", respostas));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/corrigir/{id}")
    public ResponseEntity<Resposta> corrigirResposta(
            @PathVariable UUID id,
            @RequestBody CorrecaoRespostaDTO dto) {

        Optional<Resposta> respostaCorrigida = repostaService.corrigirResposta(id, dto.getNota(), dto.getFeedback());

        return respostaCorrigida
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
