package com.leiamais.dtos;

import com.leiamais.models.Aluno;
import com.leiamais.models.Atividade;
import com.leiamais.models.Livro;
import com.leiamais.models.Professor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TurmaRegistrationDTO {
    private UUID id;
    private String nome;
    private List<Aluno> alunos;
    private List<Professor> professores;
    private List<Livro> livros;
    private List<Atividade> atividades;


}
