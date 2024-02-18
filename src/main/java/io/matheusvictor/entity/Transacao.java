package io.matheusvictor.entity;

import io.matheusvictor.dto.CreateTransaction;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "transacoes")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
    private Integer valor;
    private String tipo;
    private String descricao;

    @CreationTimestamp
    @Column(name = "efetuada_em", nullable = false, updatable = false)
    private LocalDateTime efetuadaEm;

    public Transacao(Long clienteId, Integer valor, String tipo, String descricao) {
        this.clienteId = clienteId;
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Transacao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getEfetuadaEm() {
        return efetuadaEm;
    }

    public void setEfetuadaEm(LocalDateTime efetuadaEm) {
        this.efetuadaEm = efetuadaEm;
    }

    public static Transacao createTransaction(CreateTransaction createTransaction, Long clienteId) {
        return new Transacao(clienteId, createTransaction.valor(), createTransaction.tipo(), createTransaction.descricao());
    }
}
