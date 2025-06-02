package model.dao;

import model.entities.Lote;

import java.util.List;

public interface LoteDao {

    void insert(Lote lote);

    void update(Lote lote);

    void deleteBynumero_pedido(int produtoId, String destino);  // Exemplo de chave primária alternativa

    Lote findByNumeroPedidoAndDestino(int numeroPedido, String destino); // Supondo que Produto seja chave em Lote

    List<Lote> findAll();
}