package com.leiamais.repositories;

import com.leiamais.models.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LeituraRepository extends JpaRepository<Leitura, UUID> {
}
