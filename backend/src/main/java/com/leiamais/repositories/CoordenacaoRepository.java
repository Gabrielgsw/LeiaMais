package com.leiamais.repositories;

import com.leiamais.models.Coordenacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CoordenacaoRepository extends JpaRepository<Coordenacao, UUID> {
}
