package com.leiamais.services;

import com.leiamais.models.Atividade;
import com.leiamais.models.Turma;
import com.leiamais.repositories.AtividadeRepository;
import com.leiamais.services.AtividadeService;
import com.leiamais.services.TurmaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtividadeServiceTest {

    private AtividadeRepository atividadeRepository;
    private TurmaService turmaService;
    private AtividadeService AtividadeService;

    @BeforeEach
    void setUp() {
        atividadeRepository = mock(AtividadeRepository.class);
        turmaService = mock(TurmaService.class);
        AtividadeService = new AtividadeService(atividadeRepository, turmaService);
    }

    @Test
    void deveListarTodasAsAtividades() {
        List<Atividade> atividades = List.of(new Atividade(), new Atividade());
        when(atividadeRepository.findAll()).thenReturn(atividades);

        List<Atividade> resultado = AtividadeService.listarTodas();

        assertEquals(2, resultado.size());
        verify(atividadeRepository).findAll();
    }

    @Test
    void deveBuscarAtividadePorId() {
        UUID id = UUID.randomUUID();
        Atividade atividade = new Atividade();
        when(atividadeRepository.findById(id)).thenReturn(Optional.of(atividade));

        Optional<Atividade> resultado = AtividadeService.buscarPorId(id);

        assertTrue(resultado.isPresent());
        verify(atividadeRepository).findById(id);
    }

    @Test
    void deveBuscarAtividadePorNome() {
        Atividade atividade = new Atividade();
        atividade.setNome("O pequeno principe");
        when(atividadeRepository.findAll()).thenReturn(List.of(atividade));

        Optional<Atividade> resultado = AtividadeService.buscarPorNome("O pequeno principe");

        assertTrue(resultado.isPresent());
        assertEquals("O pequeno principe", resultado.get().getNome());
    }

    @Test
    void deveSalvarAtividadeValida() {
        UUID turmaId = UUID.randomUUID();
        Atividade atividade = new Atividade();
        atividade.setPrazoEntrega(LocalDate.now().plusDays(5));
        atividade.setEnunciado(List.of("Q1", "Q2"));

        when(atividadeRepository.save(atividade)).thenReturn(atividade);

        Atividade resultado = AtividadeService.salvar(turmaId, atividade);

        assertNotNull(resultado);
        verify(atividadeRepository).save(atividade);
        verify(turmaService).adicionarAtividade(turmaId, atividade);
    }

    @Test
    void deveLancarExcecaoQuandoMaisDeTresEnunciados() {
        Atividade atividade = new Atividade();
        atividade.setEnunciado(List.of("Q1", "Q2", "Q3", "Q4"));
        atividade.setPrazoEntrega(LocalDate.now().plusDays(5));

        assertThrows(IllegalArgumentException.class, () -> AtividadeService.salvar(UUID.randomUUID(), atividade));
    }

    @Test
    void deveLancarExcecaoQuandoPrazoDeEntregaPassado() {
        Atividade atividade = new Atividade();
        atividade.setEnunciado(List.of("Q1"));
        atividade.setPrazoEntrega(LocalDate.now().minusDays(11));

        assertThrows(IllegalArgumentException.class, () -> AtividadeService.salvar(UUID.randomUUID(), atividade));
    }

    @Test
    void deveDeletarAtividadeQuandoExiste() {
        UUID id = UUID.randomUUID();
        when(atividadeRepository.existsById(id)).thenReturn(true);

        AtividadeService.deletar(id);

        verify(atividadeRepository).deleteById(id);
    }

    @Test
    void deveLancarExcecaoAoDeletarAtividadeInexistente() {
        UUID id = UUID.randomUUID();
        when(atividadeRepository.existsById(id)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> AtividadeService.deletar(id));
    }

    @Test
    void deveBuscarPorTrechoDeEnunciado() {
        Atividade a1 = new Atividade();
        a1.setEnunciado(List.of("Questão sobre A lebre e a tartaruga"));

        Atividade a2 = new Atividade();
        a2.setEnunciado(List.of("O pequeno príncipe"));

        when(atividadeRepository.findAll()).thenReturn(List.of(a1, a2));

        List<Atividade> resultado = AtividadeService.buscarPorTrechoDeEnunciado("tartaruga");

        assertEquals(1, resultado.size());
        assertTrue(resultado.get(0).getEnunciado().contains("Questão sobre A lebre e a tartaruga"));
    }
}
