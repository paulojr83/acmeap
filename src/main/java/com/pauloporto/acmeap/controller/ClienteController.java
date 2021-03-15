package com.pauloporto.acmeap.controller;
import java.util.List;
import java.util.Optional;

import com.pauloporto.acmeap.domain.Cliente;
import com.pauloporto.acmeap.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Acme AP Cliente Service")
@RequestMapping(value = "/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(final ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @ApiOperation(value = "Exibe a lista de clientes")
    //Controle de versão explicito na URI
    @GetMapping
    public List<Cliente> getAllClientes() {

        return this.clienteService.getAllClientes();
    }

    @ApiOperation(value = "Consulta um cliente pelo CPF")
    @GetMapping("/{cpf}")
    public Optional<Cliente> getClienteByCpf(@PathVariable String cpf) {
        return this.getClienteByCpf(cpf);
    }

    @ApiOperation(value = "Cadastrar um novo cliente")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> criarCliente(@RequestBody Cliente cliente){
        return this.criarCliente(cliente);
    }
}
