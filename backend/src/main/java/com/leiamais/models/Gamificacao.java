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

    public UUID getId() {
        return id;
    }


    public int getXpPorLeitura() {
        return xpPorLeitura;
    }

    public void setXpPorLeitura(int xpPorLeitura) {
        this.xpPorLeitura = xpPorLeitura;
    }

    public int getXpPorAtividade() {
        return xpPorAtividade;
    }

    public void setXpPorAtividade(int xpPorAtividade) {
        this.xpPorAtividade = xpPorAtividade;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
