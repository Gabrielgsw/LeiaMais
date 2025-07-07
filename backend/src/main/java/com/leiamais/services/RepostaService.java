package com.leiamais.services;

import com.leiamais.models.Aluno;
import com.leiamais.models.Atividade;
import com.leiamais.models.Resposta;
import com.leiamais.repositories.AlunoRepository;
import com.leiamais.repositories.AtividadeRepository;
import com.leiamais.repositories.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Aluno> buscarPorId(UUID id) {
        return alunoRepository.findById(id);
    }

    public Optional<Aluno> buscarPorMatricula(String matricula) {
        return alunoRepository.findByMatricula(matricula);
    }

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public void deletar(UUID id) {
        alunoRepository.deleteById(id);
    }

    public Optional<Aluno> buscarPorNome(String nome) {
        return alunoRepository.findByNome(nome);
    }

    public Optional<Aluno> buscarPorEmail(String email) {
        return alunoRepository.findByEmail(email);
    }

    public Optional<Atividade> findByNome(String nome) {
        List<Atividade> atividades = atividadeRepository.findAll();
        for(Atividade a : atividades) {
            if(a.getNome().equals(nome)) {
                return Optional.of(a);
            }
        }
        return null;
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
}
