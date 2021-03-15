package com.pauloporto.acmeap.service;

import com.pauloporto.acmeap.domain.Cliente;
import com.pauloporto.acmeap.exception.RecursoNotFoundException;
import com.pauloporto.acmeap.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(final ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }
    @Override
    public List<Cliente> getAllClientes() {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        try {
            listaClientes = (ArrayList<Cliente>) clienteRepository.findAll();
        } catch (Exception e) {
            throw new RecursoNotFoundException("Erro ao recuperar lista de clientes");
        }

        return listaClientes;
    }

    @Override
    public Optional<Cliente> getClienteByCpf(String cpf) {
        Optional<Cliente> cliente = Optional.ofNullable(null);

        try {
            cliente = clienteRepository.findByCpf(cpf);

            if (cliente.get() == null)
                throw new RecursoNotFoundException ("CPF inválido - " + cpf);
        } catch (Exception e) {
            throw new RecursoNotFoundException ("CPF inválido - " + cpf);
        }
        return cliente;
    }

    @Override
    public ResponseEntity<Object> criarCliente(Cliente cliente) {
        Cliente clienteCriado;
        URI location = null;
        try {
            clienteCriado = clienteRepository.save(cliente);

            location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteCriado.getId()).toUri();
        } catch (Exception e) {
            throw new RecursoNotFoundException ("Erro ao cadastrar cliente CPF: " + cliente.getCpf());
        }
        return ResponseEntity.created(location).build();
    }
}
