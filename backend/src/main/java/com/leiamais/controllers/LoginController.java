package com.leiamais.controllers;


import com.leiamais.dtos.UsuarioLoginDTO;
import com.leiamais.models.Usuario;
import com.leiamais.services.UsuarioService;
import com.leiamais.services.UsuarioSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
            return ResponseEntity.ok("Login realizado com sucesso como " + foundUsuario.getNome() + ", Cargo: " + cargo + "!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas: Senha incorreta");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser() {
        UsuarioSession session = UsuarioSession.getInstance();
        if (session.isLoggedIn()) {
            Usuario loggedInUsuario = session.getLoggedInUsuario();
            return ResponseEntity.ok("Usuario atual:"+  loggedInUsuario.getNome() + ", Cargo: " + loggedInUsuario.getCargo().name());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario atual nao encontrado.");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        UsuarioSession.getInstance().logout();
        return ResponseEntity.ok("Logout realizado com sucesso.");
    }
}
