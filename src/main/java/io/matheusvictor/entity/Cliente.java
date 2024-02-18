package io.matheusvictor.entity;

import io.matheusvictor.dto.CreateTransaction;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "clientes")
@Cacheable
public class Cliente {
    @Id
    private Long id;
    private String nome;
    private Integer saldo;
    private Integer limite;

    @OneToMany(mappedBy = "clienteId", cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    private List<Transacao> transacaos;


    public Cliente verifyTypeOfTransaction(CreateTransaction createTransaction) {
        if (createTransaction.tipo().equals("c")) {
            this.saldo += createTransaction.valor();
        }
        if (createTransaction.valor() > (this.limite + this.saldo)) {
            throw new RuntimeException("Limite excedido");
        }

        this.saldo -= createTransaction.valor();

        return this;
    }

    public Cliente addTransactionsToCliente(List<Transacao> transacoes) {
        this.transacaos = transacoes;
        return this;
    }

    public Cliente(String nome, Integer saldo, Integer limite, List<Transacao> transacaos) {
        this.nome = nome;
        this.saldo = saldo;
        this.limite = limite;
        this.transacaos = transacaos;
    }

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Integer getLimite() {
        return limite;
    }

    public void setLimite(Integer limite) {
        this.limite = limite;
    }

    public List<Transacao> getTransacaos() {
        return transacaos;
    }

    public void setTransacaos(List<Transacao> transacaos) {
        this.transacaos = transacaos;
    }
}
