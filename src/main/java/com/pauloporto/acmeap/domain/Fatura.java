package com.pauloporto.acmeap.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Data
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_instalacao")
    private Instalacao instalacao;

    @Setter @Getter
    private String codigo;

    @Setter @Getter
    private Date dataLeitura;

    @Setter @Getter
    private Date dataVencimento;

    @Setter @Getter
    private int numeroLeitura;

    @Setter @Getter
    private double valorConta;

    protected Fatura() {

    }

    public Fatura(String codigo, Date dataLeitura, Date dataVencimento, int numeroLeitura, double valorConta) {
        super();
        this.codigo = codigo;
        this.dataLeitura = dataLeitura;
        this.dataVencimento = dataVencimento;
        this.numeroLeitura = numeroLeitura;
        this.valorConta = valorConta;
    }
}
