package com.leiamais.dtos;

import com.leiamais.models.Cargo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioLoginDTO {
    private String email;
    private String password;
    private Cargo cargo;
}
