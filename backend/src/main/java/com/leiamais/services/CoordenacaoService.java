package com.leiamais.services;


import com.leiamais.models.Cargo;
import com.leiamais.models.Coordenacao;
import com.leiamais.models.Turma;
import com.leiamais.models.Usuario;
import com.leiamais.repositories.CoordenacaoRepository;
import com.leiamais.repositories.TurmaRepository;
import com.leiamais.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CoordenacaoService extends UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final TurmaRepository turmaRepository;
    private final CoordenacaoRepository coordenacaoRepository;


    public CoordenacaoService(UsuarioRepository usuarioRepository, TurmaRepository turmaRepository,CoordenacaoRepository coordenacaoRepository) {
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository;
        this.turmaRepository = turmaRepository;
        this.coordenacaoRepository = coordenacaoRepository;
    }

    public List<Turma> findAllTurmas() {
        return turmaRepository.findAll();
    }

    public Coordenacao atualizarCoordenador(UUID id, Coordenacao novoCoordenador) {
        Optional<Coordenacao> optionalCoordenador = coordenacaoRepository.findById(id);

        if (optionalCoordenador.isPresent()) {
            Coordenacao coordenadorExistente = optionalCoordenador.get();

            coordenadorExistente.setNome(novoCoordenador.getNome());
            coordenadorExistente.setEmail(novoCoordenador.getEmail());
            coordenadorExistente.setSenha(novoCoordenador.getSenha());
            coordenadorExistente.setCpf(novoCoordenador.getCpf());
            coordenadorExistente.setDataNascimento(novoCoordenador.getDataNascimento());
            coordenadorExistente.setCargo(novoCoordenador.getCargo());


            return coordenacaoRepository.save(coordenadorExistente);
        } else {
            throw new RuntimeException("Coordenador não encontrado com ID: " + id);
        }
    }
}
