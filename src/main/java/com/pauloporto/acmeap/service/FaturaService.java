package com.pauloporto.acmeap.service;

import com.pauloporto.acmeap.domain.Fatura;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface FaturaService {

    List<Fatura> getAllFaturas();
    Optional<Fatura> getFatura(String codigo);
    List<Fatura> getFaturasPorCPF(String cpf);
    ResponseEntity<Object> gerarFatura(Fatura fatura);
}
