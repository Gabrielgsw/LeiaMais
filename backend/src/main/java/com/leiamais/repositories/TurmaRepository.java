package com.leiamais.repositories;

import com.leiamais.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface TurmaRepository extends JpaRepository<Turma, UUID> { // ja existe findByID na doc do JpaRepository
    Optional<Turma> findByNome(String nome);
}
