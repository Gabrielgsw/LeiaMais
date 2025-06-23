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
public class LivroService {

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

    public Optional<Livro> buscarPorISBN(String ISBN) {
        return livroRepository.findByISBN(ISBN);
    }

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public void deletar(UUID id) {
        livroRepository.deleteById(id);
    }


}
