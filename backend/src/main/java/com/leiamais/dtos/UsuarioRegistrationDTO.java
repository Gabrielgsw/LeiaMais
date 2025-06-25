package com.leiamais.dtos;
import com.leiamais.models.Cargo;
import lombok.Data;

import java.util.Date;

@Data
public class UsuarioRegistrationDTO {
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private Cargo cargo;
    private Date dataNascimento;
    private String disciplinaMinistrada;
}