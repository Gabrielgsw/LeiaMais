package com.leiamais.services;

import com.leiamais.models.Aluno;
import com.leiamais.models.Leitura;
import com.leiamais.models.Livro;
import com.leiamais.models.StatusLivro;
import com.leiamais.repositories.LeituraRepository;
import com.leiamais.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LeituraService {

    @Autowired
    private final LeituraRepository leituraRepository;
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private LivroService livroService;

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

    @Transactional
    public Leitura criar(UUID idUser, String ISBN) {
        Optional<Leitura> leituraExistente = buscarLeitura(idUser, ISBN);
        if (leituraExistente.isPresent()) {
        System.out.println("Leitura já existente encontrada para o aluno e livro.");
        return leituraExistente.get();
    }
        Optional<Aluno> aluno = Optional.ofNullable(alunoService.buscarPorId(idUser));
        Optional<Livro> livro = livroService.findByISBN(ISBN);
        Optional<Leitura> l = buscarLeitura(idUser, ISBN);
        Leitura leitura = new Leitura();
        if(!l.isPresent()) {
            leitura.setStatus(StatusLivro.EMLEITURA);
            leitura.setAluno(aluno.get());
            leitura.setLivro(livro.get());
            aluno.get().setPontos(aluno.get().getPontos() + 1);
        }

        return leituraRepository.save(leitura);
    }



     public Optional<Leitura> buscarLeitura(UUID alunoId, String isbn) {
        return leituraRepository.findAll().stream()
            .filter(leitura -> leitura.getAluno() != null && leitura.getAluno().getId().equals(alunoId) && 
                               leitura.getLivro() != null && leitura.getLivro().getISBN().equals(isbn))
            .findFirst();
    }

    
    @Transactional
    public Leitura update(UUID id, String status, int avaliacao) {
        Leitura leituraAtualizada = leituraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leitura não encontrada com ID: " + id));

        StatusLivro novoStatus = StatusLivro.valueOf(status);
        leituraAtualizada.setStatus(novoStatus);
        leituraAtualizada.setAvaliacao(avaliacao);
        
        Aluno aluno = leituraAtualizada.getAluno();

        // Lógica para atribuir pontos apenas uma vez
        if (novoStatus == StatusLivro.CONCLUIDO && !leituraAtualizada.isPontoConcluidoConcedido()) {
            int pontosGanhos = 0;
            // Se o ponto inicial ainda não foi dado (ex: mudou de 'Não iniciado' para 'Concluído')
            if (!leituraAtualizada.isPontoConcluidoConcedido()) {
                pontosGanhos += 1;
                leituraAtualizada.setPontoEmLeituraConcedido(true);
            }
            // Atribui os 2 pontos finais
            pontosGanhos += 2;
            aluno.setPontos(aluno.getPontos() + pontosGanhos);
            aluno.setQtdLivrosLidos(aluno.getQtdLivrosLidos() + 1);
            leituraAtualizada.setPontoConcluidoConcedido(true);
            System.out.println("Leitura concluída. Atribuídos " + pontosGanhos + " pontos.");
        } else if (novoStatus == StatusLivro.EMLEITURA && !leituraAtualizada.isPontoEmLeituraConcedido()) {
            // Se o utilizador mudar o status para 'Em Leitura' pela primeira vez
            aluno.setPontos(aluno.getPontos() + 1);
            leituraAtualizada.setPontoEmLeituraConcedido(true);
            System.out.println("Status 'Em Leitura' definido. Atribuído 1 ponto inicial.");
        }

        return leituraRepository.save(leituraAtualizada);
    }


    public void delete(UUID id) {
        leituraRepository.deleteById(id);
    }
}
