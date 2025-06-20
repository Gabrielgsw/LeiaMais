package com.leiamais.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Usuario {


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

}
