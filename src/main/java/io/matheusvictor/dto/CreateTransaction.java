package io.matheusvictor.dto;

public record CreateTransaction(
        Integer valor,
        String tipo,
        String descricao
) {
    public String validate() {
        if (descricao == null || descricao.isBlank() || descricao.isEmpty() || descricao.length() > 10) {
            return "Descrição inválida";
        }
        if (tipo == null || (!tipo.equals("c") && !tipo.equals("d"))) {
            return "Tipo inválido";
        }
        if (valor == null || valor <= 0) {
            return "Valor inválido";
        }
        return null;
    }
}
