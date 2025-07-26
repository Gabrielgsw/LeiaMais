package com.leiamais.controllers;

import com.leiamais.models.Aluno;
import com.leiamais.models.Leitura;
import com.leiamais.models.Livro;
import com.leiamais.models.StatusLivro;
import com.leiamais.services.LivroService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/livros")
@CrossOrigin(origins = "http://localhost:5173")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping("/formulario")
    public ResponseEntity<String> receberFormulario(@RequestBody Map<String, String> form) {
        String nome = form.get("nome");
        String email = form.get("email");
        String numero = form.get("numero");
        String mensagem = form.get("mensagem");

        System.out.println("Recebido:");
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Número: " + numero);
        System.out.println("Mensagem: " + mensagem);

        return ResponseEntity.ok("Formulário recebido com sucesso!");
    }


    @GetMapping
    public ResponseEntity<List<Livro>> listarTodos() {
        return ResponseEntity.ok(livroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable UUID id) {
        Optional<Livro> livroOptional = livroService.buscarPorId(id);
        if (livroOptional.isPresent()) {
            return ResponseEntity.ok(livroOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Livro> buscarPorISBN(@PathVariable String isbn) {
        Optional<Livro> livroOptional = livroService.findByISBN(isbn);
        if (livroOptional.isPresent()) {
            return ResponseEntity.ok(livroOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Livro> criar(@RequestBody Livro livro) {
        System.out.println("Recebido: " + livro.getTitulo());
        Livro salvo = livroService.salvar(livro);
        System.out.println("Salvo: " + salvo.getISBN());
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable UUID id, @RequestBody Livro livro  ) {
        Optional<Livro> livroOptional = livroService.buscarPorId(id);
        if (livroOptional.isPresent()) {
            livro.setId(id);
            Livro livroAtualizado = livroService.salvar(livro);
            return ResponseEntity.ok(livroAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }*/

    @GetMapping("/{isbn}/pdf")
    public ResponseEntity<byte[]> exportarPDF(@PathVariable String isbn) throws IOException {

        try{
            Optional<Livro> livro = livroService.findByISBN(isbn);
            String fileId = livro.get().getLivroUrl();
            String driveUrl = "https://drive.usercontent.google.com/u/0/uc?id="+fileId+"&export=download";
            URL url = new URL(driveUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            byte[] pdfBytes = conn.getInputStream().readAllBytes();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.inline().filename("livro.pdf").build());

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }

    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deletarByISBN(@PathVariable String isbn) {
        Optional<Livro> livro = livroService.findByISBN(isbn);
        if (livro.isPresent()) {
            livroService.deletar(livro.get().getId());
        }
        return ResponseEntity.noContent().build();
    }
}
