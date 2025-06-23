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
public class Professor extends Usuario {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private Disciplina disciplinaMinistrada;


    public Professor(String cpf, String nome, String email, String senha, Cargo cargo, Date dataNascimento, UUID id, Disciplina disciplinaMinistrada) {
        super(cpf, nome, email, senha, cargo, dataNascimento);
        this.id = id;
        this.disciplinaMinistrada = disciplinaMinistrada;
    }

    public UUID getId() {
        return id;
    }


    public Disciplina getDisciplinaMinistrada() {
        return disciplinaMinistrada;
    }

    public void setDisciplinaMinistrada(Disciplina disciplinaMinistrada) {
        this.disciplinaMinistrada = disciplinaMinistrada;
    }
}
