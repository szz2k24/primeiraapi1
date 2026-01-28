package com.primeiraapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.primeiraapi.model.Pessoa;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    // métodos customizados podem ser adicionados aqui
    Optional<Pessoa> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}

