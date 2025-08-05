package com.leiamais.models;

public enum Disciplina

{
    HISTORIA("História"),MATEMATICA("Matemática"),PORTUGUES("Português"),FILOSOFIA("Filosofia"),SOCIOLOGIA("Sociologia"),GEOGRAFIA("Geografia");

    private String disciplina;

    Disciplina(String disciplina){
        this.disciplina = disciplina;
    }

    @Override
    public String toString(){
        return this.disciplina;
    }

}
