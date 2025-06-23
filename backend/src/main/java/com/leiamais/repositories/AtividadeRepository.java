package com.leiamais.repositories;

import com.leiamais.models.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, UUID> {
    Optional<Atividade> findByEnunciado(String enunciado);
    // Outros métodos de consulta podem ser adicionados aqui conforme necessário
}