package com.leiamais.services;


import com.leiamais.models.Cargo;
import com.leiamais.models.Usuario;
import com.leiamais.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CoordenacaoService extends UsuarioService{

    private final UsuarioRepository usuarioRepository;

    public CoordenacaoService(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository;
    }

}
