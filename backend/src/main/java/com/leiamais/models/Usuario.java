package com.leiamais.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "cargo_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario {

    @Id
    @GeneratedValue
    private UUID id;

    @Column//(unique = true,nullable = false)
    private String cpf;
    @Column//(unique = true,nullable = false)
    private String nome;
    @Column//(unique = true,nullable = false)
    private String email;
    @Column//(nullable = false)
    private String senha;
    @Column
    private Cargo cargo;
    @Column
    private Date dataNascimento;

    public Usuario(String cpf, String nome, String email, String senha, Cargo cargo,Date dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.dataNascimento = dataNascimento;
        this.id = UUID.randomUUID();
    }

    public Cargo getCargo() {
        if (this instanceof Aluno) {
            return Cargo.ALUNO;
        } else if (this instanceof Professor) {
            return Cargo.PROFESSOR;
        } else if (this instanceof Coordenacao) {
            return Cargo.COORDENADOR;
        }
        return null;
    }
}
