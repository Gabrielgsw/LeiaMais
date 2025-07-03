package com.leiamais.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue
    private UUID id;
    @Column//(nullable = false)
    private String titulo;
    @Column//(unique = true)
    private String ISBN;
    @Column//(nullable = false)
    private String autor;
    @Column//(nullable = false)
    private String editora;
    @Column
    private String livroUrl;

    public String getLivroUrl() {
        return livroUrl;
    }

    public void setLivroUrl(String livroUrl) {
        this.livroUrl = livroUrl;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
}
