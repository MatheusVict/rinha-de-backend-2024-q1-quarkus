package io.matheusvictor.repository;

import io.matheusvictor.entity.Transacao;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TransacaoRepository implements PanacheRepository<Transacao> {
    public Uni<List<Transacao>> find10LastTransactionsOrderedByDate(Long clienteId) {
        return find("cliente_id = ?1 ORDER BY efetuada_em DESC LIMIT 10", clienteId)
                .list();
    }
}
