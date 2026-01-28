package com.primeiraapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.primeiraapi.model.Pessoa;
import com.primeiraapi.service.PessoaService;

/**
 * Controller responsável por expor os endpoints REST da entidade Pessoa.
 * Aqui tratamos as requisições HTTP (GET, POST, PUT, DELETE)
 * e definimos as mensagens que o cliente irá receber.
 */
@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/api/pessoas")
public class TesteController {

    private final PessoaService service;

    // Injeção de dependência via construtor (boa prática)
    public TesteController(PessoaService service) {
        this.service = service;
    }

    /**
     * 1️⃣ BUSCAR TODAS AS PESSOAS
     * - Se a lista estiver vazia, retorna mensagem amigável
     */
    @GetMapping
    public ResponseEntity<?> listarTodos() {
        List<Pessoa> pessoas = service.findAll();

        return ResponseEntity.ok(pessoas);
    }

    /**
     * 2️⃣ BUSCAR PESSOA POR ID
     * - Se não encontrar, o próprio service lança erro 404
     */
    @GetMapping("/{id}")
 public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
     Pessoa pessoa = service.findOrThrow(id);
     return ResponseEntity.ok(pessoa);
     
 }


    /**
     * 3️⃣ CADASTRAR NOVA PESSOA
     * - Retorna os dados cadastrados + mensagem de sucesso
     */
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Pessoa pessoa) {
    Pessoa salva = service.create(pessoa);

    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(salva.getId())
            .toUri();

    return ResponseEntity
            .created(location)
            .body(
                Map.of(
                    "pessoa", salva,
                    "mensagem", "Pessoa adicionado com sucesso."
                )
            );
}

    /**
     * 4️⃣ ATUALIZAR PESSOA
     * - Atualiza somente os campos enviados
     * - Retorna os novos dados + mensagem
     */
@PutMapping("/{id}")
public ResponseEntity<?> atualizar(@PathVariable Long id,@RequestBody Pessoa pessoaAtualizada) {

    try {
        Pessoa atualizada = service.update(id, pessoaAtualizada);

        return ResponseEntity.ok(
                Map.of(
                        "pessoa", atualizada,
                        "mensagem", "Pessoa alterada com sucesso"
                )
        );

    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(404)
                .body("Pessoa não encontrada");
    }
}



    /**
     * 5️⃣ REMOVER PESSOA
     * - Após a exclusão, retorna mensagem de confirmação
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Pessoa removida com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Pessoa não encontrada");
        }
    }

    
}
