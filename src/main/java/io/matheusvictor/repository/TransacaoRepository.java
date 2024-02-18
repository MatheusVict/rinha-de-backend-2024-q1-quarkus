package io.matheusvictor.repository;

import io.matheusvictor.entity.Transacao;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TransacaoRepository implements PanacheRepository<Transacao> {
    public Uni<List<Transacao>> find10LastTransactionsOrderedByDate(Long clienteId) {
        return find("clienteId = ?1 order by efetuada_em desc LIMIT 10", clienteId)

                .list();
    }
}
