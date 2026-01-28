package com.primeiraapi.service;

import com.primeiraapi.model.Pessoa;
import com.primeiraapi.repository.PessoaRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço:
 * - Contém as regras de negócio
 * - Centraliza validações
 * - Evita lógica pesada no Controller
 */
@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    /**
     * Cria uma nova pessoa
     * - Valida CPF duplicado
     */
    public Pessoa create(Pessoa pessoa) {
        if (pessoa.getCpf() != null && repository.existsByCpf(pessoa.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        return repository.save(pessoa);
    }

    /**
     * Retorna todas as pessoas cadastradas
     */
    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    /**
     * Busca por ID retornando Optional
     */
    public Optional<Pessoa> findById(@NonNull Long id) {
        return repository.findById(id);
    }


       
    /**
     * Busca por ID ou lança erro 404
     */
    public Pessoa findOrThrow(@NonNull Long id) {
        return repository.findById(id)
            .orElseThrow(() ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Pessoa não encontrada"
                )
            );
    }

    /**
     * Atualiza pessoa existente
     * - Atualiza SOMENTE campos que vieram no JSON
     * - Evita apagar dados sem querer
     */
    public Pessoa update(@NonNull Long id, Pessoa dados) {
        Pessoa existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada"));

        if (dados.getNome() != null)
            existente.setNome(dados.getNome());

        if (dados.getEmail() != null)
            existente.setEmail(dados.getEmail());

        if (dados.getTelefone() != null)
            existente.setTelefone(dados.getTelefone());

        if (dados.getDataNascimento() != null)
            existente.setDataNascimento(dados.getDataNascimento());

        if (dados.getCpf() != null)
            existente.setCpf(dados.getCpf());

        return repository.save(existente);
    }

    /**
     * Remove pessoa pelo ID
     */
    public void delete(@NonNull Long id) {
        repository.deleteById(id);
    }

  

}

