package com.leiamais.dtos;
import java.util.UUID;
import com.leiamais.models.Cargo;
import lombok.Data;

@Data
public class LoggedInUsuarioDTO {
    private UUID id;
    private String nome;
    private String email;
    private Cargo cargo;
}