package com.leiamais.models;

public enum Cargo {

    COORDENADOR("Coordenador"),ALUNO("Aluno"),PROFESSOR("Professor");

   private String cargo;

   Cargo(String cargo) {
       this.cargo = cargo;
   }

    @Override
    public String toString() {
        return "Cargo{" +
                "cargo='" + cargo + '\'' +
                '}';
    }
}
