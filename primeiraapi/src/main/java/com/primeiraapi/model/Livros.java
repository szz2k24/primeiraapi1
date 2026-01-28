package com.primeiraapi.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "livros")
public class Livros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long isbn;

    @NotBlank(message = "O nome do livro é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Column(name = "data_emissao")
    private LocalDate dataEmissao;

    @NotBlank(message = "O autor é obrigatório")
    @Column(nullable = false)
    private String autor;

    public Livros() {
    }

    public Livros(String nome, LocalDate dataEmissao, String autor) {
        this.nome = nome;
        this.dataEmissao = dataEmissao;
        this.autor = autor;
    }

    // getters e setters
    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
