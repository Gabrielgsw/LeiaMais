package com.leiamais.controllers;


import com.leiamais.dtos.UsuarioLoginDTO;
import com.leiamais.dtos.LoggedInUsuarioDTO;
import com.leiamais.models.Usuario;
import com.leiamais.services.UsuarioService;
import com.leiamais.services.UsuarioSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(value = "http://localhost:5173",allowCredentials = "true")
@RequestMapping("/api/auth")
public class LoginController {

    private final UsuarioService usuarioService; //

    @Autowired
    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO ) {
        String email = usuarioLoginDTO.getEmail();
        String password = usuarioLoginDTO.getPassword();
        String cargo = String.valueOf(usuarioLoginDTO.getCargo());

        Optional<Usuario> usuarioOptional = usuarioService.findByEmail(email);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas: Usuário não encontrado");
        }

        Usuario foundUsuario = usuarioOptional.get();
        if (foundUsuario.getSenha().equals(password)) {
            UsuarioSession.getInstance().setLoggedInUsuario(foundUsuario);
            System.out.println(UsuarioSession.getInstance().getLoggedInUsuario().getNome());
            return ResponseEntity.ok("Login realizado com sucesso como " + foundUsuario.getNome() + ", Cargo: " + cargo + "!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas: Senha incorreta");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<LoggedInUsuarioDTO> getCurrentUser() {
        UsuarioSession session = UsuarioSession.getInstance();
        if (session.isLoggedIn()) {
            Usuario loggedInUsuario = session.getLoggedInUsuario();
            
            // 1. Instancie o DTO corretamente
            LoggedInUsuarioDTO dto = new LoggedInUsuarioDTO();
            
            // 2. Preencha o DTO com as informações
            dto.setId(loggedInUsuario.getId());
            dto.setNome(loggedInUsuario.getNome());
            dto.setEmail(loggedInUsuario.getEmail());
            dto.setCargo(loggedInUsuario.getCargo());
            
            // 3. Retorne o DTO diretamente. O Spring irá convertê-lo para JSON.
        return ResponseEntity.ok(dto);
    }
    
    // O retorno de erro pode ser um objeto JSON também para consistência
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
}

    @GetMapping("/me/id")
    public ResponseEntity<UUID> getIdCurrentUser(){
        UsuarioSession session = UsuarioSession.getInstance();
        if (session.isLoggedIn()) {
            Usuario loggedInUsuario = session.getLoggedInUsuario();
            return ResponseEntity.ok(loggedInUsuario.getId());
        }
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        UsuarioSession.getInstance().logout();
        return ResponseEntity.ok("Logout realizado com sucesso.");
    }
}
