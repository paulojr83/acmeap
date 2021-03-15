package com.pauloporto.acmeap.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Setter @Getter
    private String logradouro;

    @Setter @Getter
    private long numero;

    @Setter @Getter
    private String bairro;

    @Setter @Getter
    private String cidade;

    @Setter @Getter
    private String uf;

    protected Endereco() {

    }

    public Endereco(String logradouro, String bairro, String cidade, String uf) {
        super();
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }
}