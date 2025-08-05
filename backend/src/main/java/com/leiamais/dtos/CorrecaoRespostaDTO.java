package com.leiamais.dtos;

public class CorrecaoRespostaDTO {

    private Float nota;
    private String feedback;

    public CorrecaoRespostaDTO() {
    }

    public CorrecaoRespostaDTO(Float nota, String feedback) {
        this.nota = nota;
        this.feedback = feedback;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}