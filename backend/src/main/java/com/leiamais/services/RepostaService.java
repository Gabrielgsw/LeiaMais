package com.leiamais.services;

import com.leiamais.models.Aluno;
import com.leiamais.models.Atividade;
import com.leiamais.models.Resposta;
import com.leiamais.repositories.AlunoRepository;
import com.leiamais.repositories.AtividadeRepository;
import com.leiamais.repositories.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RepostaService {
    private final AlunoRepository alunoRepository;
    private final AtividadeRepository atividadeRepository;
    private final RespostaRepository respostaRepository;

    public RepostaService(AlunoRepository alunoRepository, AtividadeRepository atividadeRepository, RespostaRepository respostaRepository) {
        this.alunoRepository = alunoRepository;
        this.atividadeRepository = atividadeRepository;
        this.respostaRepository = respostaRepository;
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

    public Optional<Atividade> findByNome(String nome) {
        List<Atividade> atividades = atividadeRepository.findAll();
        for(Atividade a : atividades) {
            if(a.getNome().equals(nome)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    public Resposta responderAtividade(Aluno aluno, String nomeAtividade, String texto){
        Optional<Atividade> atv = findByNome(nomeAtividade);
        if(atv.isPresent()) {
            Resposta resp = new Resposta();
            resp.setAtividade(atv.get());
            resp.setAluno(aluno);
            //resp.setTexto(texto);
            return resp;
        }
        return null;    

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
                .filter(resposta -> resposta.getAluno().equals(aluno) && resposta.getAtividade().equals(atividade))
                .findFirst();
    }
    public Collection<Resposta> findAll() {
        return respostaRepository.findAll();
    }
}
