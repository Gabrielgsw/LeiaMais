package com.leiamais.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Coordenacao extends Usuario {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany
    List<Turma> turmasCoordenadas;

    public Coordenacao(String cpf, String nome, String email, String senha, Cargo cargo, Date dataNascimento, UUID id) {
        super(cpf, nome, email, senha, cargo, dataNascimento);
        this.id = id;
        this.turmasCoordenadas = new ArrayList<>();
    }


    public UUID getId() {
        return id;
    }

    public List<Turma> getTurmasCoordenadas() {
        return turmasCoordenadas;
    }


}
