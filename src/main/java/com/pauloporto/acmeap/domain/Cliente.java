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
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco enderecoCobranca;

    //@JsonIgnore
    @Setter @Getter
    @JsonBackReference
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Instalacao> listaInstalacao = new ArrayList<Instalacao>();

    @Setter @Getter
    private String nome;

    @Setter @Getter
    private String cpf;

    @Setter @Getter
    private Date dataNascimento;

    protected Cliente() {

    }

    public Cliente(String nome, String cpf, Date dataNascimento) {
        super();
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }
}
