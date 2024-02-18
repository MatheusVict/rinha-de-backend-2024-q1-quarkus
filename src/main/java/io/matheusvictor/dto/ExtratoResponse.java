package io.matheusvictor.dto;

import io.matheusvictor.entity.Cliente;
import io.matheusvictor.entity.Transacao;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RegisterForReflection
public record ExtratoResponse(
        ClienteResponse saldo,
        List<ResponseLastTransactions> ultimas_transacoes
) {

    @RegisterForReflection
    public record ClienteResponse(
            Integer total,
            LocalDateTime data_extrato,
            Integer limite
    ) {}


    @RegisterForReflection
    public record ResponseLastTransactions(
            Integer valor,
            String tipo,
            String descricao,
            LocalDateTime efetuada_em
    ) {}

    public static ExtratoResponse createNewResponse(Cliente cliente) {
        List<ResponseLastTransactions> lastTransactions = new ArrayList<>(cliente.getTransacaos().size());

        for (Transacao transacao: cliente.getTransacaos()) {
            lastTransactions.add(new ResponseLastTransactions(
                    transacao.getValor(),
                    transacao.getTipo(),
                    transacao.getDescricao(),
                    transacao.getEfetuada_em()
            ));
        }

        return new ExtratoResponse(
                new ClienteResponse(cliente.getSaldo(), LocalDateTime.now(), cliente.getLimite()),
                lastTransactions
        );
    }
}
