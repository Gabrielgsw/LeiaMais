package com.leiamais.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Gamificacao {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private int xpPorLeitura;
    @Column(nullable = false)
    private int xpPorAtividade;
    @Column
    private int nivel;


}
