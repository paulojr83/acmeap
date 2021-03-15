package com.pauloporto.acmeap.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Instalacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco enderecoInstalacao;

    //@JsonIgnore
    @Getter @Setter
    @JsonBackReference
    @OneToMany(mappedBy = "instalacao", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fatura> listaFatura = new ArrayList<Fatura>();

    @Getter @Setter
    private String codigo;

    @Getter @Setter
    private Date dataInstalacao;

    protected Instalacao() {

    }

    public Instalacao(String codigo, Date dataInstalacao) {
        super();
        this.codigo = codigo;
        this.dataInstalacao = dataInstalacao;
    }
}
