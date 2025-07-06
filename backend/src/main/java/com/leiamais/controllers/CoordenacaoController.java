package com.leiamais.controllers;


import com.leiamais.dtos.TurmaRegistrationDTO;
import com.leiamais.dtos.UsuarioRegistrationDTO;
import com.leiamais.models.*;
import com.leiamais.services.CoordenacaoService;
import com.leiamais.services.TurmaService;
import com.leiamais.services.UsuarioService;
import com.leiamais.services.UsuarioSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/coordenador")
@CrossOrigin(origins = "http://localhost:5173")
public class CoordenacaoController {

    private final UsuarioService usuarioService;
    private final TurmaService turmaService;
    private final CoordenacaoService coordenacaoService;

    @Autowired
    public CoordenacaoController(UsuarioService usuarioService, CoordenacaoService coordenacaoService, TurmaService turmaService) {
        this.usuarioService = usuarioService;
        this.coordenacaoService = coordenacaoService;
        this.turmaService = turmaService;
    }

    @PostMapping("/register-turma")
    public ResponseEntity<Turma> registerTurmas(@RequestBody TurmaRegistrationDTO dto){

            UsuarioSession session = UsuarioSession.getInstance();
            if(!session.isLoggedIn()){
                return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            }

            if(session.getLoggedInUsuario().getCargo() != Cargo.COORDENADOR) {
                return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            }

            if(dto.getId() == null || dto.getNome() == null){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            Turma turma = new Turma();

            BeanUtils.copyProperties(dto, turma);

            try {
                Turma registeredTurma = turmaService.RegistrarTurma(turma);
                return new ResponseEntity<>(registeredTurma, HttpStatus.OK);
            }catch (IllegalArgumentException e) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT); // Nome de usuário/email já existe
            } catch (Exception e) {
                e.printStackTrace(); // Logar o erro para depuração
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }


    @PostMapping("/register-user")
    public ResponseEntity<Usuario> registerUser(@RequestBody UsuarioRegistrationDTO dto) {

        UsuarioSession session = UsuarioSession.getInstance();
        if (!session.isLoggedIn()) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        if (dto.getNome() == null || dto.getNome().isEmpty() ||
                dto.getSenha() == null || dto.getSenha().isEmpty() ||
                dto.getEmail() == null || dto.getEmail().isEmpty() ||
                dto.getCpf() == null || dto.getCpf().isEmpty() ||
                dto.getCargo() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Usuario newUsuario = null;


        switch (dto.getCargo()) {
            case ALUNO:
                newUsuario = new Aluno();
                break;
            case PROFESSOR:
                newUsuario = new Professor();
                break;
            case COORDENADOR:
                newUsuario = new Coordenacao();
                break;
            default:
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Cargo inválido
        }

        BeanUtils.copyProperties(dto, newUsuario, "disciplinaMinistrada");


        if (newUsuario instanceof Professor && dto.getDisciplinaMinistrada() != null) {
            ((Professor) newUsuario).setDisciplinaMinistrada(Disciplina.valueOf(dto.getDisciplinaMinistrada()));
        }

        try {
            Usuario registeredUsuario = usuarioService.RegistrarUsuario(newUsuario);
            return new ResponseEntity<>(registeredUsuario, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT); // Nome de usuário/email já existe
        } catch (Exception e) {
            e.printStackTrace(); // Logar o erro para depuração
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable("id") UUID id) {

        UsuarioSession session = UsuarioSession.getInstance(); // instanciando UsuarioSession
        if (!session.isLoggedIn()) { // se nao estiver logado

            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        if (session.getLoggedInUsuario().getCargo() != Cargo.COORDENADOR) { // se nao for coordenador

            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        try {

            Optional<Usuario> usuarioOptional = usuarioService.findById(id);

            if (!usuarioOptional.isPresent()) { // se o user encontrado nao estiver presente

                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            Usuario usuario = usuarioOptional.get();


            if (usuario.getId().equals(session.getLoggedInUsuario().getId())) { // se o user tentar se deletar (apenas coordenador pode deletar um user, não ele mesmo)

                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }


            usuarioService.deletar(id);

            return new ResponseEntity<>(usuario, HttpStatus.OK); // se der certo, deu certo e retorna ok
        } catch( Exception e) {
            System.err.println("Erro ao deletar: " + e.getMessage()); // printando a mensagem de erro
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // retornar que deu algum Internal Server Error
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        UsuarioSession session = UsuarioSession.getInstance();
        if (!session.isLoggedIn()) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        List<Usuario> usuarios = usuarioService.findAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/turmas")
    public ResponseEntity<List<Turma>> getAllTurmas() {
        UsuarioSession session = UsuarioSession.getInstance();
        if (!session.isLoggedIn()) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        List<Turma> turmas = coordenacaoService.findAllTurmas();
        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deletarCoordenador(@PathVariable("id") UUID id){
        Optional<Usuario> coordenador = usuarioService.findById(id);

        if(coordenador.isPresent()){
            usuarioService.deletar(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }

}