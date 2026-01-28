package com.primeiraapi.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "emprestimos")
public class Emprestimos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "isbn_livro", nullable = false)
    private Livros livro;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuarios usuario;

    @Column(nullable = false)
    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucao;

    public Emprestimos() {}

    public Emprestimos(Livros livro, Usuarios usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
    }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Livros getLivro() { return livro; }
    public void setLivro(Livros livro) { this.livro = livro; }

    public Usuarios getUsuario() { return usuario; }
    public void setUsuario(Usuarios usuario) { this.usuario = usuario; }

    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(LocalDate dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }

    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }
}
