package com.leiamais.services;

import com.leiamais.models.Aluno;
import com.leiamais.models.Professor;
import com.leiamais.models.Usuario;
import com.leiamais.repositories.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfessorService {

    private final UsuarioRepository usuarioRepository;

    public ProfessorService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        List<Usuario> professores = new ArrayList<>();
        List<Usuario> users = usuarioRepository.findAll();
        for(Usuario usuario : users) {
            if(usuario.getClass().equals(Professor.class)) {
                professores.add(usuario);
            }
        }


        return professores;
    }

    public Optional<Usuario> buscarPorId(UUID id) {
        return usuarioRepository.findById(id);
    }

    public Usuario salvar(Professor professor) {
        return usuarioRepository.save(professor);
    }

    public void deletar(UUID id) {
        usuarioRepository.deleteById(id);
    }
}
