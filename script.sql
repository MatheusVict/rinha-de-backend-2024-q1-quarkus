DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS transacoes;


CREATE TABLE IF NOT EXISTS clientes
(
    id     INTEGER PRIMARY KEY NOT NULL,
    nome   VARCHAR(25)         NOT NULL,
    saldo  INTEGER             NOT NULL,
    limite INTEGER             NOT NULL
);

CREATE TABLE IF NOT EXISTS transacoes
(
    id         SERIAL PRIMARY KEY,
    cliente_id  integer     NOT NULL,
    tipo       char(1)     NOT NULL,
    valor      integer     NOT NULL,
    descricao  varchar(10) NOT NULL,
    efetuada_em timestamp   NOT NULL
);

CREATE INDEX fk_transacao_clienteid ON transacoes
    (
     cliente_id ASC
    );

DELETE
FROM transacoes;
DELETE
FROM clientes;

INSERT INTO clientes (id, nome, saldo, limite)
VALUES (1, 'o barato sai caro', 0, 1000 * 100),
       (2, 'zan corp ltda', 0, 800 * 100),
       (3, 'les cruders', 0, 10000 * 100),
       (4, 'padaria joia de cocaia', 0, 100000 * 100),
       (5, 'kid mais', 0, 5000 * 100);