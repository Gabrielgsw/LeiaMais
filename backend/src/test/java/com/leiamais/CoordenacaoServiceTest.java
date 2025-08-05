package com.leiamais;

import com.leiamais.models.Cargo;
import com.leiamais.models.Coordenacao;
import com.leiamais.models.Turma;
import com.leiamais.repositories.CoordenacaoRepository;
import com.leiamais.repositories.TurmaRepository;
import com.leiamais.repositories.UsuarioRepository;
import com.leiamais.services.CoordenacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CoordenacaoServiceTest {

    private UsuarioRepository usuarioRepository;
    private TurmaRepository turmaRepository;
    private CoordenacaoRepository coordenacaoRepository;
    private CoordenacaoService coordenacaoService;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        turmaRepository = mock(TurmaRepository.class);
        coordenacaoRepository = mock(CoordenacaoRepository.class);
        coordenacaoService = new CoordenacaoService(usuarioRepository, turmaRepository, coordenacaoRepository);
    }

    @Test
    void deveListarTodasAsTurmas() {
        List<Turma> turmas = List.of(new Turma(), new Turma());
        when(turmaRepository.findAll()).thenReturn(turmas);

        List<Turma> resultado = coordenacaoService.findAllTurmas();

        assertEquals(2, resultado.size());
        verify(turmaRepository).findAll();
    }

    @Test
    void deveAtualizarCoordenadorQuandoEncontrado() {
        UUID id = UUID.randomUUID();
        Coordenacao coordenadorAntigo = new Coordenacao();
        coordenadorAntigo.setId(id);

        Coordenacao novoCoordenador = new Coordenacao();
        novoCoordenador.setNome("Novo Nome");
        novoCoordenador.setEmail("novo@email.com");
        novoCoordenador.setSenha("senha123");
        novoCoordenador.setCpf("12345678900");
        novoCoordenador.setDataNascimento(Date.valueOf(LocalDate.of(1980, 1, 1)));
        novoCoordenador.setCargo(Cargo.COORDENADOR);

        when(coordenacaoRepository.findById(id)).thenReturn(Optional.of(coordenadorAntigo));
        when(coordenacaoRepository.save(any(Coordenacao.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Coordenacao resultado = coordenacaoService.atualizarCoordenador(id, novoCoordenador);

        assertNotNull(resultado);
        assertEquals("Novo Nome", resultado.getNome());
        assertEquals("novo@email.com", resultado.getEmail());
        assertEquals("senha123", resultado.getSenha());
        assertEquals("12345678900", resultado.getCpf());
        assertEquals(LocalDate.of(1980, 1, 1), resultado.getDataNascimento());
        assertEquals(Cargo.COORDENADOR, resultado.getCargo());

        verify(coordenacaoRepository).save(coordenadorAntigo);
    }

    @Test
    void deveLancarExcecaoSeCoordenadorNaoForEncontrado() {
        UUID id = UUID.randomUUID();
        Coordenacao novoCoordenador = new Coordenacao();

        when(coordenacaoRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> coordenacaoService.atualizarCoordenador(id, novoCoordenador));

        assertTrue(exception.getMessage().contains("Coordenador não encontrado"));
    }
}
