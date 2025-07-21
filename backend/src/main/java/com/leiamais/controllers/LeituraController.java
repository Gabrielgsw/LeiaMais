package com.leiamais.controllers;

import com.leiamais.models.Leitura;
import com.leiamais.services.LeituraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/leituras")
public class LeituraController {

    @Autowired
    private final LeituraService leituraService;

    public LeituraController(LeituraService leituraService) {
        this.leituraService = leituraService;
    }


    @GetMapping
    public ResponseEntity<List<Leitura>> listarTodasLeituras() {
        return ResponseEntity.ok(leituraService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leitura> listarLeiturarPorId(@PathVariable UUID id) {
        return leituraService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Leitura> criar(@RequestBody Leitura leitura) {
        return ResponseEntity.ok(leituraService.criar(leitura));
    }



    @PutMapping("/{id}/{status}")
    public ResponseEntity<Leitura> atualizarStatus(@PathVariable UUID id, @PathVariable String status) {
        try {
            return ResponseEntity.ok(leituraService.update(id, status));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        leituraService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
