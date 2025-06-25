package com.leiamais.models;

import jakarta.persistence.*;
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
@NoArgsConstructor
@DiscriminatorValue("COORDENADOR")
public class Coordenacao extends Usuario {



    @OneToMany
    List<Turma> turmasCoordenadas;

    public Coordenacao(String cpf, String nome, String email, String senha, Cargo cargo, Date dataNascimento, UUID id) {
        super(cpf, nome, email, senha, cargo, dataNascimento);
        this.turmasCoordenadas = new ArrayList<>();
    }


    public List<Turma> getTurmasCoordenadas() {
        return turmasCoordenadas;
    }


}
