package com.pauloporto.acmeap.service;

import com.pauloporto.acmeap.domain.Cliente;
import com.pauloporto.acmeap.domain.Fatura;
import com.pauloporto.acmeap.domain.Instalacao;
import com.pauloporto.acmeap.exception.RecursoNotFoundException;
import com.pauloporto.acmeap.repository.ClienteRepository;
import com.pauloporto.acmeap.repository.FaturaRepository;
import com.pauloporto.acmeap.repository.InstalacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FaturaServiceImpl implements FaturaService{

    private final FaturaRepository faturaRepository;
    private final InstalacaoRepository instalacaoRepository;
    private final ClienteRepository clienteRepository;

    public FaturaServiceImpl(final FaturaRepository faturaRepository,
                             final InstalacaoRepository instalacaoRepository,
                             final ClienteRepository clienteRepository){
        this.faturaRepository = faturaRepository;
        this.instalacaoRepository = instalacaoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Fatura> getAllFaturas() {
        ArrayList<Fatura> listaFaturas = new ArrayList<Fatura>();
        try {
            listaFaturas = (ArrayList<Fatura>) faturaRepository.findAll();
        } catch (Exception e) {
            throw new RecursoNotFoundException("Erro ao recuperar faturas");
        }
        return listaFaturas;
    }

    @Override
    public Optional<Fatura> getFatura(String codigo) {
        Optional<Fatura> fatura = Optional.ofNullable(null);

        try {
            fatura = faturaRepository.findByCodigo(codigo);
            if (fatura.get() == null)
                throw new RecursoNotFoundException ("codigo de fatura inválido - " + codigo);
        } catch (Exception e) {
            throw new RecursoNotFoundException ("codigo de fatura inválido - " + codigo);
        }
        return fatura;
    }

    @Override
    public List<Fatura> getFaturasPorCPF(String cpf) {
        Optional<Cliente> cliente = Optional.ofNullable(null);
        List<Instalacao> listaInstalacao;

        try {
            cliente = clienteRepository.findByCpf(cpf);
            if (cliente.get() == null)
                throw new RecursoNotFoundException ("CPF - " + cpf);

            listaInstalacao = instalacaoRepository.findByCliente(cliente.get());
        } catch (Exception e) {
            throw new RecursoNotFoundException ("CPF inválido - " + cpf);
        }


        List<Fatura> listaFaturasCliente = new ArrayList<Fatura>();

        listaInstalacao.stream()
                .forEach(item -> item.getListaFatura().stream().forEach(fatura -> listaFaturasCliente.add(fatura)));

        return listaFaturasCliente;
    }

    @Override
    public ResponseEntity<Object> gerarFatura(Fatura fatura) {
        Optional<Instalacao> instalacaoRecuperada = Optional.ofNullable(null);
        URI location = null;

        try {

            instalacaoRecuperada = instalacaoRepository.findByCodigo(fatura.getInstalacao().getCodigo());
            if (instalacaoRecuperada.get() == null)
                throw new RecursoNotFoundException ("codigo instalacao - " + fatura.getInstalacao().getCodigo());

            fatura.setInstalacao(instalacaoRecuperada.get());

            Fatura faturaCriada = faturaRepository.save(fatura);
            location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(faturaCriada.getId()).toUri();

        } catch (Exception e) {
            throw new RecursoNotFoundException ("Erro ao gerar fatura para a instalacao - " + fatura.getInstalacao().getCodigo());
        }
        return ResponseEntity.created(location).build();
    }
}
