package com.leiamais.repositories;

import com.leiamais.models.Atividade;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, UUID> {
    Optional<Atividade> findByEnunciado(String enunciado);
    @EntityGraph(attributePaths = "respostas")
    @Query("SELECT a FROM Atividade a WHERE a.id = :id")
    Optional<Atividade> findByIdComRespostas(UUID id);
    // Outros métodos de consulta podem ser adicionados aqui conforme necessário
}