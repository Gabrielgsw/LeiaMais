package com.leiamais.repositories;

import com.leiamais.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface TurmaRepository extends JpaRepository<Turma, UUID> { // ja existe findByID na doc do JpaRepository
    Optional<Turma> findByNome(String nome);
}
