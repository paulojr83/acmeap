package com.pauloporto.acmeap.service;

import com.pauloporto.acmeap.domain.Instalacao;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface InstalacaoService {

    List<Instalacao> getAllInstalacoes();
    Optional<Instalacao> getInstalacao(String codigo);
    List<Instalacao> getInstalacaoPorCPF(String cpf);
    ResponseEntity<Object> cadastrarInstalacao(Instalacao instalacao);
}
