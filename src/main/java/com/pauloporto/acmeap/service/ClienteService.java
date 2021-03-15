package com.pauloporto.acmeap.service;

import com.pauloporto.acmeap.domain.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClienteService {

    List<Cliente> getAllClientes();
    Optional<Cliente> getClienteByCpf(String cpf);
    ResponseEntity<Object> criarCliente(Cliente cliente);
}
