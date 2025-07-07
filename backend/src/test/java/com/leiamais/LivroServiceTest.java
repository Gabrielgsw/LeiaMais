package com.leiamais;

import com.leiamais.models.Livro;
import com.leiamais.repositories.LivroRepository;
import com.leiamais.services.LivroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LivroServiceTest {

    private LivroRepository livroRepository;
    private LivroService livroService;

    @BeforeEach
    void setUp() {
        livroRepository = mock(LivroRepository.class);
        livroService = new LivroService(livroRepository);
    }

    @Test
    void listarLivros() {
        List<Livro> livrosMock = Arrays.asList(new Livro(), new Livro());
        when(livroRepository.findAll()).thenReturn(livrosMock);

        List<Livro> resultado = livroService.listarTodos();

        assertEquals(2, resultado.size());
        verify(livroRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId() {
        UUID id = UUID.randomUUID();
        Livro livro = new Livro();
        livro.setId(id);

        when(livroRepository.findById(id)).thenReturn(Optional.of(livro));

        Optional<Livro> resultado = livroService.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(id, resultado.get().getId());
    }

    @Test
    void salvarLivro() {
        Livro livro = new Livro();
        livro.setTitulo("Livro Teste");

        when(livroRepository.save(livro)).thenReturn(livro);

        Livro salvo = livroService.salvar(livro);

        assertNotNull(salvo);
        assertEquals("Livro Teste", salvo.getTitulo());
    }

    @Test
    void deletarLivro() {
        UUID id = UUID.randomUUID();

        doNothing().when(livroRepository).deleteById(id);

        livroService.deletar(id);

        verify(livroRepository, times(1)).deleteById(id);
    }

    @Test
    void buscaPorISBN() {
        Livro livro1 = new Livro();
        livro1.setISBN("123");

        Livro livro2 = new Livro();
        livro2.setISBN("456");

        when(livroRepository.findAll()).thenReturn(List.of(livro1, livro2));

        Optional<Livro> resultado = livroService.findByISBN("123");

        assertTrue(resultado.isPresent());
        assertEquals("123", resultado.get().getISBN());
    }

    @Test
    void buscaISBNnaoExistente() {
        Livro livro = new Livro();
        livro.setISBN("999");

        when(livroRepository.findAll()).thenReturn(List.of(livro));

        Optional<Livro> resultado = livroService.findByISBN("000");

        assertTrue(resultado.isEmpty());
    }
}
