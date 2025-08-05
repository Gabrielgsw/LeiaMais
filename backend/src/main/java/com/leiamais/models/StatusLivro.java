package com.leiamais.models;

public enum StatusLivro {

    NAOINICIADO("Não iniciado"),
    EMLEITURA("Em leitura"),
    CONCLUIDO("Concluído");

    private String statusLivro;
     StatusLivro(String statusLivro) {
         this.statusLivro = statusLivro;
     }

    @Override
    public String toString() {
        return "StatusLivro{" +
                "statusLivro='" + statusLivro + '\'' +
                '}';
    }
}
