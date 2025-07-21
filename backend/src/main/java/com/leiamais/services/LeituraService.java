package com.leiamais.services;

import com.leiamais.models.Aluno;
import com.leiamais.models.Leitura;
import com.leiamais.models.StatusLivro;
import com.leiamais.repositories.LeituraRepository;
import com.leiamais.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LeituraService {

    @Autowired
    private final LeituraRepository leituraRepository;
    private final UsuarioRepository usuarioRepository;

    public LeituraService (LeituraRepository leituraRepository, UsuarioRepository usuarioRepository) {
        this.leituraRepository =  leituraRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Leitura> findAll() {
        return leituraRepository.findAll();
    }

    public Optional<Leitura> findById(UUID id) {
        return leituraRepository.findById(id);
    }

    public Leitura criar(Leitura leitura) {
        leitura.setStatus(StatusLivro.EMLEITURA);
        return leituraRepository.save(leitura);
    }

    public Leitura update(UUID id, String status) {
        Optional<Leitura> leituraOptional = leituraRepository.findById(id);

        if (leituraOptional.isEmpty()) {
            throw new RuntimeException("Leitura não encontrada com ID: " + id);
        }

        //Leitura leituraExistente = leituraOptional.get();
        Leitura leituraAtualizada = findById(id).get(); // Se "findById" também for do tipo Optional

        //leituraExistente.setLivro(leituraAtualizada.getLivro());
        //leituraExistente.setAluno(leituraAtualizada.getAluno());
        leituraAtualizada.setStatus(StatusLivro.valueOf(status));
        if(leituraAtualizada.getStatus() == StatusLivro.CONCLUIDO){
            Aluno aluno = leituraAtualizada.getAluno();
            aluno.setQtdLivrosLidos(aluno.getQtdLivrosLidos() + 1 ); // incrementando quantidade de livros
            aluno.setPontos(aluno.getPontos() + 2 ); // adicionando 2 pontos por leitura concluida
        }

        return leituraRepository.save(leituraAtualizada);
    }


    public void delete(UUID id) {
        leituraRepository.deleteById(id);
    }
}
