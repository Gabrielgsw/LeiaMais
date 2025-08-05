/*package com.leiamais;

import com.leiamais.models.Aluno;
import com.leiamais.models.Atividade;
import com.leiamais.models.Resposta;
import com.leiamais.repositories.AlunoRepository;
import com.leiamais.repositories.AtividadeRepository;
import com.leiamais.repositories.RespostaRepository;
import com.leiamais.services.RepostaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RepostaServiceTest {

    private AlunoRepository alunoRepository;
    private AtividadeRepository atividadeRepository;
    private RespostaRepository respostaRepository;
    private RepostaService repostaService;

    @BeforeEach
    void setUp() {
        alunoRepository = mock(AlunoRepository.class);
        atividadeRepository = mock(AtividadeRepository.class);
        respostaRepository = mock(RespostaRepository.class);
        repostaService = new RepostaService(alunoRepository, atividadeRepository, respostaRepository);
    }

    @Test
    void deveListarTodasAsRespostas() {
        List<Resposta> respostas = List.of(new Resposta(), new Resposta());
        when(respostaRepository.findAll()).thenReturn(respostas);

        List<Resposta> resultado = repostaService.listarRespostas();

        assertEquals(2, resultado.size());
        verify(respostaRepository).findAll();
    }

    @Test
    void deveBuscarRespostaPorId() {
        UUID id = UUID.randomUUID();
        Resposta resposta = new Resposta();
        when(respostaRepository.findById(id)).thenReturn(Optional.of(resposta));

        Optional<Resposta> resultado = repostaService.buscarRespostaPorId(id);

        assertTrue(resultado.isPresent());
        verify(respostaRepository).findById(id);
    }

    @Test
    void deveSalvarResposta() {
        Resposta resposta = new Resposta();
        repostaService.salvarResposta(resposta);
        verify(respostaRepository).save(resposta);
    }

    @Test
    void deveExcluirResposta() {
        UUID id = UUID.randomUUID();
        repostaService.excluirResposta(id);
        verify(respostaRepository).deleteById(id);
    }

    @Test
    void deveAtualizarResposta() {
        Resposta resposta = new Resposta();
        repostaService.atualizarResposta(resposta);
        verify(respostaRepository).save(resposta);
    }

    @Test
    void deveEncontrarAtividadePorNome() {
        Atividade atividade = new Atividade();
        atividade.setNome("Prova 1");

        when(atividadeRepository.findAll()).thenReturn(List.of(atividade));

        Optional<Atividade> resultado = repostaService.findByNome("Prova 1");

        assertTrue(resultado.isPresent());
        assertEquals("Prova 1", resultado.get().getNome());
    }

    @Test
    void naoDeveEncontrarAtividadeSeNomeNaoExistir() {
        when(atividadeRepository.findAll()).thenReturn(List.of());

        Optional<Atividade> resultado = repostaService.findByNome("Inexistente");

        assertFalse(resultado.isPresent());
    }

    @Test
    void deveResponderAtividadeQuandoEncontrada() {
        Aluno aluno = new Aluno();
        Atividade atividade = new Atividade();
        atividade.setNome("Atividade 1");

        when(atividadeRepository.findAll()).thenReturn(List.of(atividade));

        Resposta resposta = repostaService.responderAtividade(aluno, "Atividade 1", "resposta");

        assertNotNull(resposta);
        assertEquals(aluno, resposta.getAluno());
        assertEquals(atividade, resposta.getAtividade());
    }

    @Test
    void deveRetornarNullSeAtividadeNaoForEncontradaAoResponder() {
        Aluno aluno = new Aluno();
        when(atividadeRepository.findAll()).thenReturn(List.of());

        Resposta resposta = repostaService.responderAtividade(aluno, "Inexistente", "resposta");

        assertNull(resposta);
    }

    @Test
    void deveCorrigirResposta() {
        UUID id = UUID.randomUUID();
        Resposta resposta = new Resposta();
        when(respostaRepository.findById(id)).thenReturn(Optional.of(resposta));

        Optional<Resposta> resultado = repostaService.corrigirResposta(id, 9.5f, "Bom trabalho");

        assertTrue(resultado.isPresent());
        assertEquals(9.5f, resultado.get().getNota());
        assertEquals("Bom trabalho", resultado.get().getFeedback());
        verify(respostaRepository).save(resposta);
    }

    @Test
    void naoDeveCorrigirSeRespostaNaoForEncontrada() {
        UUID id = UUID.randomUUID();
        when(respostaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Resposta> resultado = repostaService.corrigirResposta(id, 8.0f, "OK");

        assertFalse(resultado.isPresent());
    }

    @Test
    void deveBuscarRespostaPorAlunoEAtividade() {
        Aluno aluno = new Aluno();
        Atividade atividade = new Atividade();

        Resposta resposta = new Resposta();
        resposta.setAluno(aluno);
        resposta.setAtividade(atividade);

        when(respostaRepository.findAll()).thenReturn(List.of(resposta));

        Optional<Resposta> resultado = repostaService.findByAlunoAndAtividade(aluno, atividade);

        assertTrue(resultado.isPresent());
        assertEquals(aluno, resultado.get().getAluno());
        assertEquals(atividade, resultado.get().getAtividade());
    }

    @Test
    void deveRetornarTodasRespostas() {
        List<Resposta> respostas = List.of(new Resposta());
        when(respostaRepository.findAll()).thenReturn(respostas);

        Collection<Resposta> resultado = repostaService.findAll();

        assertEquals(1, resultado.size());
    }
}

 */
