package com.leiamais.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Atividade {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String enunciado;
    @Column
    private float nota;
    @Column
    private String feedback;
    @OneToOne
    private Resposta resposta;

}
