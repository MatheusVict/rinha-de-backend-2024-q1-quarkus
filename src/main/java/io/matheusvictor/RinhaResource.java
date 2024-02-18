package io.matheusvictor;

import io.matheusvictor.dto.CreateTransaction;
import io.matheusvictor.dto.TransactionResponse;
import io.matheusvictor.entity.Cliente;
import io.matheusvictor.entity.Transacao;
import io.matheusvictor.repository.ClienteRepository;
import io.matheusvictor.repository.TransacaoRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.persistence.LockModeType;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
public class RinhaResource {

    private final ClienteRepository clienteRepository;

    private final TransacaoRepository transacaoRepository;

    public RinhaResource(ClienteRepository clienteRepository, TransacaoRepository transacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @POST
    @Path("/{id}/transacoes")
    @Consumes(MediaType.APPLICATION_JSON)
    @WithTransaction
    public Uni<Response> createTransaction(@PathParam("id") Long id, CreateTransaction createTransaction) {
        if (createTransaction.validate() != null) {
            return Uni.createFrom().item(Response.status(422).entity(createTransaction.validate()).build());
        }

        if (id > 5 || id < 1) {
            return Uni.createFrom().item(Response.status(404).entity("Cliente não encontrado").build());
        }

        return clienteRepository.findById(id, LockModeType.PESSIMISTIC_WRITE)
                .onItem()
                .ifNotNull()
                .transformToUni(cliente -> {
                    Cliente clienteUpdated = cliente.verifyTypeOfTransaction(createTransaction);
                    Transacao transactionToCreate = Transacao.createTransaction(createTransaction, clienteUpdated.getId());

                    return Uni.combine()
                            .all()
                            .unis(transacaoRepository.persist(transactionToCreate), clienteRepository.persist(clienteUpdated))
                            .asTuple()
                            .map(tuple -> Response.ok().entity(new TransactionResponse(tuple.getItem2().getLimite(), tuple.getItem2().getSaldo())).build());
                })
                .onItem().ifNull().continueWith(Response.status(404)::build)
                .onFailure().recoverWithItem(Response.status(422).build());
    }
}
