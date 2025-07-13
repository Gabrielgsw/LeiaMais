package com.leiamais;

import com.leiamais.models.Atividade;
import com.leiamais.models.Professor;
import com.leiamais.models.Turma;
import com.leiamais.repositories.AtividadeRepository;
import com.leiamais.services.AtividadeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtividadeServiceTest {

    @Mock
    private AtividadeRepository atividadeRepository;

    @InjectMocks
    private AtividadeService atividadeService;

    private Atividade atividade;

    @BeforeEach
    void setUp() {
        atividade = new Atividade();
        atividade.setId(UUID.randomUUID());
        atividade.setNome("Atividade de Matemática");
        atividade.setEnunciado(List.of("Questão 1", "Questão 2"));
        atividade.setPrazoEntrega(LocalDate.now().plusDays(3));
        atividade.setProfessor(new Professor());
        atividade.setTurma(new Turma());
    }

    @Test
    void salvarAtividadeSucess() {
        when(atividadeRepository.save(ArgumentMatchers.any(Atividade.class)))
                .thenReturn(atividade);

        Atividade resultado = atividadeService.salvar(atividade);

        assertNotNull(resultado);
        assertEquals("Atividade: Pequeno Príncipe", resultado.getNome());
        verify(atividadeRepository, times(1)).save(atividade);
    }

    @Test
    void exepcionAtividadeEnunciado() {
        atividade.setEnunciado(List.of("Q1", "Q2", "Q3", "Q4"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            atividadeService.salvar(atividade);
        });

        assertEquals("Cada atividade pode ter no máximo 3 enunciados.", exception.getMessage());
        verify(atividadeRepository, never()).save(any());
    }

    @Test
    void exceptionPrazoEntrega() {
        atividade.setPrazoEntrega(LocalDate.now().minusDays(15));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            atividadeService.salvar(atividade);
        });

        assertEquals("O prazo de entrega não pode ser no passado.", exception.getMessage());
        verify(atividadeRepository, never()).save(any());
    }

    @Test
    void buscaAtividadeExistente() {
        UUID id = atividade.getId();
        when(atividadeRepository.findById(id)).thenReturn(Optional.of(atividade));

        Optional<Atividade> resultado = atividadeService.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals("Atividade: Pequeno Príncipe", resultado.get().getNome());
    }

    @Test
    void listarAtividades() {
        when(atividadeRepository.findAll()).thenReturn(List.of(atividade));

        List<Atividade> resultado = atividadeService.listarTodas();

        assertEquals(1, resultado.size());
    }

    @Test
    void listarPorNome() {
        when(atividadeRepository.findAll()).thenReturn(List.of(atividade));

        Optional<Atividade> resultado = atividadeService.buscarPorNome("Atividade: Pequeno Príncipe");

        assertTrue(resultado.isPresent());
        assertEquals("Atividade: Pequeno Príncipe", resultado.get().getNome());
    }

    @Test
    void buscaPorTurma() {
        when(atividadeRepository.findAll()).thenReturn(List.of(atividade));

        List<Atividade> resultado = atividadeService.buscarPorTurma(atividade.getTurma().getId());

        assertEquals(1, resultado.size());
    }

    @Test
    void exclusaoAtividade() {
        UUID id = atividade.getId();
        when(atividadeRepository.existsById(id)).thenReturn(true);

        atividadeService.deletar(id);

        verify(atividadeRepository, times(1)).deleteById(id);
    }

    @Test
    void exceptionExclusaoAtividade() {
        UUID id = UUID.randomUUID();
        when(atividadeRepository.existsById(id)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            atividadeService.deletar(id);
        });

        assertEquals("Atividade com ID " + id + " não encontrada.", exception.getMessage());
        verify(atividadeRepository, never()).deleteById(any());
    }

    @Test
    void buscaAtividadeEnunciado() {
        when(atividadeRepository.findAll()).thenReturn(List.of(atividade));

        List<Atividade> resultado = atividadeService.buscarPorTrechoDeEnunciado("questão");

        assertEquals(1, resultado.size());
    }
}
