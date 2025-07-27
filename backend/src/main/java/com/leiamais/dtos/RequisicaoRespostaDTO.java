package com.leiamais.dtos;

import java.util.List;
import java.util.UUID;

import com.leiamais.models.Atividade;

public class RequisicaoRespostaDTO {
    private Atividade atividade;
    private UUID alunoId;
    private List<RespostaItemDTO> respostas;


    public UUID getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(UUID alunoId) {
        this.alunoId = alunoId;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividadeId(Atividade atividadeId) {
        this.atividade = atividadeId;
    }

    public List<RespostaItemDTO> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<RespostaItemDTO> respostas) {
        this.respostas = respostas;
    }

    public static class RespostaItemDTO {
        private UUID enunciadoId;
        private String resposta;

        public UUID getEnunciadoId() { return enunciadoId; }
        public void setEnunciadoId(UUID enunciadoId) {  this.enunciadoId = enunciadoId; }
        public String getResposta() { return resposta; }
        public void setResposta(String resposta) { this.resposta = resposta; }
    }
}
