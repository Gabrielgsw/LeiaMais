package com.leiamais.services;

import com.leiamais.dtos.RequisicaoRespostaDTO;
import com.leiamais.models.Aluno;
import com.leiamais.models.Atividade;
import com.leiamais.models.Resposta;
import com.leiamais.repositories.AlunoRepository;
import com.leiamais.repositories.AtividadeRepository;
import com.leiamais.repositories.RespostaRepository;

import org.springframework.transaction.annotation.Transactional; 

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RepostaService {
    private final AlunoRepository alunoRepository;
    private final AtividadeRepository atividadeRepository;
    private final RespostaRepository respostaRepository;
    private final AtividadeService atividadeService;

    public RepostaService(AlunoRepository alunoRepository, AtividadeRepository atividadeRepository, RespostaRepository respostaRepository,AtividadeService atividadeService) {
        this.alunoRepository = alunoRepository;
        this.atividadeRepository = atividadeRepository;
        this.respostaRepository = respostaRepository;
        this.atividadeService = atividadeService;
    }

    public List<Resposta> listarRespostas() {
        return respostaRepository.findAll();
    }

    public Optional<Resposta> buscarRespostaPorId(UUID id) {
        return respostaRepository.findById(id);
    }

    public void salvarResposta(Resposta resposta) {
        respostaRepository.save(resposta);
    }

    public void excluirResposta(UUID id) {
        respostaRepository.deleteById(id);
    }

    public void atualizarResposta(Resposta resposta) {
        respostaRepository.save(resposta);
    }

    

    @Transactional
    public Resposta responderAtividade(RequisicaoRespostaDTO dto) {
        // 1. Busca as entidades dentro da transação para garantir que estão "vivas" (managed).
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno com ID " + dto.getAlunoId() + " não encontrado."));

        Atividade atividade = atividadeService.buscarPorId(dto.getAtividade().getId())
                .orElseThrow(() -> new RuntimeException("Atividade com ID " + dto.getAtividade().getId() + " não encontrada."));

        Resposta novaResposta = new Resposta();
        novaResposta.setAluno(aluno);
        novaResposta.setAtividade(atividade);

        List<String> respostasTexto = dto.getRespostas()
                .stream()
                .map(RequisicaoRespostaDTO.RespostaItemDTO::getResposta)
                .collect(Collectors.toList());
        novaResposta.setRespostas(respostasTexto);

        Resposta respostaSalva = respostaRepository.save(novaResposta);
        if (atividade.getRespostas() == null) {
            atividade.setRespostas(new ArrayList<>());
        }
        atividade.getRespostas().add(respostaSalva);
        
        return respostaSalva;
    }
    
    public Optional<Resposta> corrigirResposta(UUID idResposta, float nota, String feedback) {
        Optional<Resposta> respostaOpt = respostaRepository.findById(idResposta);
        if (respostaOpt.isPresent()) {
            Resposta resposta = respostaOpt.get();
            resposta.setNota(nota);
            resposta.setFeedback(feedback);
            respostaRepository.save(resposta);
            return Optional.of(resposta);
        }
        return Optional.empty();
    }
    public Optional<Resposta> findByAlunoAndAtividade(Aluno aluno, Atividade atividade){
        return findAll().stream()
                .filter(resposta -> resposta.getAluno().equals(aluno) && resposta.getAtividade().getId().equals(atividade.getId()))
                .findFirst();
    }
    public Collection<Resposta> findAll() {
        return respostaRepository.findAll();
    }
}
