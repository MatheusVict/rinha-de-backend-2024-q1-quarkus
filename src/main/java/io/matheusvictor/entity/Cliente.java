package io.matheusvictor.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "clientes")
public class Cliente {
    @Id
    private Long id;
    private String nome;
    private Integer saldo;
    private Integer limite;

    @OneToMany(mappedBy = "clienteId", cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    private List<Transacao> transacaos;
}
