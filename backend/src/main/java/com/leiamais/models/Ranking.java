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
public class Ranking {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Turma turma;

}
