package io.matheusvictor.repository;

import io.matheusvictor.entity.Transacao;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransacaoRepository implements PanacheRepository<Transacao> {
}
