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

    private Long cliente_id;
    private Integer valor;
    private String tipo;
    private String descricao;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime efetuada_em;

    public Transacao(Long cliente_id, Integer valor, String tipo, String descricao) {
        this.cliente_id = cliente_id;
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

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
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

    public LocalDateTime getEfetuada_em() {
        return efetuada_em;
    }

    public void setEfetuada_em(LocalDateTime efetuada_em) {
        this.efetuada_em = efetuada_em;
    }

    public static Transacao createTransaction(CreateTransaction createTransaction, Long clienteId) {
        return new Transacao(clienteId, createTransaction.valor(), createTransaction.tipo(), createTransaction.descricao());
    }
}
