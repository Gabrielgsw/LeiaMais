package com.leiamais.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("PROFESSOR")
public class Professor extends Usuario {

    @Column//(nullable = false)
    private Disciplina disciplinaMinistrada;


    public Professor(String cpf, String nome, String email, String senha, Cargo cargo, Date dataNascimento, Disciplina disciplinaMinistrada, UUID id) {
        super(cpf, nome, email, senha, cargo, dataNascimento);
        this.disciplinaMinistrada = disciplinaMinistrada;
    }

}
