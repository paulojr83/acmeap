package com.pauloporto.acmeap.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pauloporto.acmeap.domain.Cliente;
import com.pauloporto.acmeap.domain.Fatura;
import com.pauloporto.acmeap.domain.Instalacao;
import com.pauloporto.acmeap.exception.RecursoNotFoundException;
import com.pauloporto.acmeap.repository.ClienteRepository;
import com.pauloporto.acmeap.repository.FaturaRepository;
import com.pauloporto.acmeap.repository.InstalacaoRepository;
import com.pauloporto.acmeap.service.ClienteService;
import com.pauloporto.acmeap.service.FaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Acme AP Fatura Service", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(value = "/v1/faturas")
public class FaturaController {

    private final FaturaService faturaService;

    public FaturaController(final FaturaService faturaService){
        this.faturaService = faturaService;
    }

    @ApiOperation(value = "Mostra a lista de faturas")
    // Controle de versão explicito na URI
    @GetMapping
    public List<Fatura> getAllFaturas() {
        return this.faturaService.getAllFaturas();
    }

    @ApiOperation(value = "Consulta uma fatura pelo código")
    @GetMapping("/{codigo}")
    public Optional<Fatura> getFatura(@PathVariable String codigo) {
        return this.faturaService.getFatura(codigo);
    }

    @ApiOperation(value = "Consulta as faturas pelo CPF do cliente")
    @GetMapping("/cpf/{cpf}")
    public List<Fatura> getFaturasPorCPF(@PathVariable String cpf) {
        return this.faturaService.getFaturasPorCPF(cpf);
    }

    @ApiOperation(value = "Gerar uma nova fatura")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> gerarFatura(@RequestBody Fatura fatura) {
        return this.faturaService.gerarFatura(fatura);
    }

}