package com.leiamais.services;

import com.leiamais.models.Cargo;
import com.leiamais.models.Usuario;
import com.leiamais.repositories.TurmaRepository;
import com.leiamais.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public Usuario RegistrarUsuario(Usuario usuario) {
        if(usuarioRepository.findByNome(usuario.getNome()).isPresent()) {
            throw new IllegalArgumentException("Nome ja registrado");
        }
        if(usuario.getCargo() == null){
            usuario.setCargo(Cargo.ALUNO);
        }

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findByNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }
    public Optional<Usuario> findByEmail(String email) {return usuarioRepository.findByEmail(email); }
    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }
}
