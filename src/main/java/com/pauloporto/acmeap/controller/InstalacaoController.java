package com.pauloporto.acmeap.controller;
import java.util.List;
import java.util.Optional;

import com.pauloporto.acmeap.domain.Instalacao;
import com.pauloporto.acmeap.service.InstalacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Acme AP Instalação Service")
@RequestMapping(value = "/v1/instalacoes")
public class InstalacaoController {

    private final InstalacaoService instalacaoService;

    public InstalacaoController(InstalacaoService instalacaoService) {
        this.instalacaoService = instalacaoService;
    }

    @ApiOperation(value = "Mostra a lista de instalações")
    //Controle de versão explicito na URI
    @GetMapping
    public List<Instalacao> getAllInstalacoes() {
        return this.instalacaoService.getAllInstalacoes();
    }


    @ApiOperation(value = "Consulta uma instalação pelo código")
    @GetMapping("/{codigo}")
    public Optional<Instalacao> getInstalacao(@PathVariable String codigo) {
        return this.instalacaoService.getInstalacao(codigo);
    }

    @ApiOperation(value = "Consulta uma instalação pelo CPF")
    @GetMapping("/cpf/{cpf}")
    public List<Instalacao> getInstalacaoPorCPF(@PathVariable String cpf) {
        return this.instalacaoService.getInstalacaoPorCPF(cpf);
    }

    @ApiOperation(value = "Cadastrar uma nova instalação")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> cadastrarInstalacao(@RequestBody Instalacao instalacao) {
        return this.instalacaoService.cadastrarInstalacao(instalacao);
    }
}