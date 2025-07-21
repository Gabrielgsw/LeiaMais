package com.leiamais.services;

import com.leiamais.models.Leitura;
import com.leiamais.models.StatusLivro;
import com.leiamais.repositories.LeituraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LeituraService {

    @Autowired
    private final LeituraRepository leituraRepository;

    public LeituraService (LeituraRepository leituraRepository){
        this.leituraRepository =  leituraRepository;
    }

    public List<Leitura> findAll() {
        return leituraRepository.findAll();
    }

    public Optional<Leitura> findById(UUID id) {
        return leituraRepository.findById(id);
    }

    public Leitura criar(Leitura leitura) {
        leitura.setStatus(StatusLivro.EMLEITURA);
        return leituraRepository.save(leitura);
    }

    public Leitura update(UUID id,String status) {
        Leitura leituraAtualizada = findById(id).get();
        return leituraRepository.findById(id).map(leitura -> {
            leitura.setLivro(leituraAtualizada.getLivro());
            leitura.setAluno(leituraAtualizada.getAluno());
            leitura.setStatus(StatusLivro.valueOf(status));
            return leituraRepository.save(leitura);
        }).orElseThrow(() -> new RuntimeException("Leitura não encontrada com ID: " + id));
    }

    public void delete(UUID id) {
        leituraRepository.deleteById(id);
    }
}
