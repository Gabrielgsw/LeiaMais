package com.leiamais.repositories;

import com.leiamais.models.Aluno;
import com.leiamais.models.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, UUID> {
    //Optional<Aluno> findByMatricula(String matricula);
}