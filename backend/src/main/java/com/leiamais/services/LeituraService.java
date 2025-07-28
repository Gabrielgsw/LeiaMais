package com.leiamais.services;

import com.leiamais.models.Aluno;
import com.leiamais.models.Leitura;
import com.leiamais.models.Livro;
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

    public Leitura criar(UUID idUser, String ISBN) {
        Optional<Aluno> aluno = Optional.ofNullable(alunoService.buscarPorId(idUser));
        Optional<Livro> livro = livroService.findByISBN(ISBN);
        Optional<Leitura> l = Optional.ofNullable(buscarLeitura(idUser, ISBN));
        Leitura leitura = new Leitura();
        if(!l.isPresent()) {
            leitura.setStatus(StatusLivro.EMLEITURA);
            leitura.setAluno(aluno.get());
            leitura.setLivro(livro.get());
            aluno.get().setPontos(aluno.get().getPontos() + 1);
        }

        return leituraRepository.save(leitura);
    }



    public Leitura buscarLeitura(UUID idUser, String isbn){
        Leitura leituraRetorno = null;
        //Aluno a = alunoService.buscarPorId(idUser);
        for(Leitura leitura :leituraRepository.findAll()){
            if(leitura.getAluno().getId().equals(idUser) && leitura.getLivro().getISBN().equals(isbn)){
                leituraRetorno = leitura;
            }
        }

        return leituraRetorno;
    }

    public Leitura update(UUID id, String status,int avaliacao) {
        Optional<Leitura> leituraOptional = leituraRepository.findById(id);

        if (leituraOptional.isEmpty()) {
            throw new RuntimeException("Leitura não encontrada com ID: " + id);
        }

        //Leitura leituraExistente = leituraOptional.get();
        Leitura leituraAtualizada = findById(id).get(); // Se "findById" também for do tipo Optional

        //leituraExistente.setLivro(leituraAtualizada.getLivro());
        //leituraExistente.setAluno(leituraAtualizada.getAluno());
        leituraAtualizada.setStatus(StatusLivro.valueOf(status));
        leituraAtualizada.setAvaliacao(avaliacao);
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
