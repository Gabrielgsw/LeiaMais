package com.leiamais.services;


import com.leiamais.models.Cargo;
import com.leiamais.models.Turma;
import com.leiamais.models.Usuario;
import com.leiamais.repositories.TurmaRepository;
import com.leiamais.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordenacaoService extends UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final TurmaRepository turmaRepository;

    public CoordenacaoService(UsuarioRepository usuarioRepository, TurmaRepository turmaRepository) {
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository;
        this.turmaRepository = turmaRepository;
    }

    public List<Turma> findAllTurmas() {
        return turmaRepository.findAll();
    }
}
