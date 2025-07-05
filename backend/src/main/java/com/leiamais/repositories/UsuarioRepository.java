package com.leiamais.repositories;

import com.leiamais.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByNome(String nome);
    Optional<Usuario> findByEmail(String email);
}
