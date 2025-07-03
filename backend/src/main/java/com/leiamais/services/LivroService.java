package com.leiamais.services;

import com.leiamais.models.Aluno;
import com.leiamais.models.Livro;
import com.leiamais.repositories.AlunoRepository;
import com.leiamais.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LivroService{

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarPorId(UUID id) {
        return livroRepository.findById(id);
    }


    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public void deletar(UUID id) {
        livroRepository.deleteById(id);
    }


    public Optional<Livro> findByISBN(String ISBN) {
        for(Livro livro : livroRepository.findAll()) {
            if(livro.getISBN().equals(ISBN)) {
                return Optional.of(livro);
            }        }

        return Optional.empty();
    }
}
