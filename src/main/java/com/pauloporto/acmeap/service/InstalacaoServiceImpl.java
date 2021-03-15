package com.pauloporto.acmeap.service;

import com.pauloporto.acmeap.domain.Cliente;
import com.pauloporto.acmeap.domain.Instalacao;
import com.pauloporto.acmeap.exception.RecursoNotFoundException;
import com.pauloporto.acmeap.repository.ClienteRepository;
import com.pauloporto.acmeap.repository.InstalacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstalacaoServiceImpl implements InstalacaoService{

    private final InstalacaoRepository instalacaoRepository;
    private final ClienteRepository clienteRepository;

    public InstalacaoServiceImpl(InstalacaoRepository instalacaoRepository, ClienteRepository clienteRepository) {
        this.instalacaoRepository = instalacaoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Instalacao> getAllInstalacoes() {
        ArrayList<Instalacao> listaInstalacoes = new ArrayList<Instalacao>();

        try {
            listaInstalacoes = (ArrayList<Instalacao>) instalacaoRepository.findAll();
        } catch (Exception e) {
            throw new RecursoNotFoundException("Erro ao recuperar lista de instalações");
        }
        return listaInstalacoes;
    }

    @Override
    public Optional<Instalacao> getInstalacao(String codigo) {
        Optional<Instalacao> instalacao = Optional.ofNullable(null);

        try {
            instalacao = instalacaoRepository.findByCodigo(codigo);
            if (instalacao.get() == null)
                throw new RecursoNotFoundException ("codigo instalacao - " + codigo);
        } catch (Exception e) {
            throw new RecursoNotFoundException ("codigo instalacao - " + codigo);
        }

        return instalacao;
    }

    @Override
    public List<Instalacao> getInstalacaoPorCPF(String cpf) {
        Optional<Cliente> cliente = Optional.ofNullable(null);
        List<Instalacao> listaInstalacao = null;

        try {
            cliente = clienteRepository.findByCpf(cpf);
            if (cliente.get() == null)
                throw new RecursoNotFoundException ("CPF - " + cpf);

            listaInstalacao = instalacaoRepository.findByCliente(cliente.get());
        } catch (Exception e) {
            throw new RecursoNotFoundException ("CPF inválido - " + cpf);
        }
        return listaInstalacao;
    }

    @Override
    public ResponseEntity<Object> cadastrarInstalacao(Instalacao instalacao) {
        Optional<Cliente> cliente = Optional.ofNullable(null);
        Instalacao instalacaoCriada;
        URI location = null;
        try {
            cliente = clienteRepository.findByCpf(instalacao.getCliente().getCpf());

            if (cliente.get() == null)
                throw new RecursoNotFoundException ("CPF - " + instalacao.getCliente().getCpf());

            instalacao.setCliente(cliente.get());

            instalacaoCriada = instalacaoRepository.save(instalacao);
            location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(instalacaoCriada.getId()).toUri();
        }
        catch (Exception e) {
            throw new RecursoNotFoundException ("CPF - " + instalacao.getCliente().getCpf());
        }
        return ResponseEntity.created(location).build();
    }
}
