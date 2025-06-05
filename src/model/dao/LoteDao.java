package model.dao;

import model.entities.Lote;
import model.entities.Pedido;

import java.util.List;

public interface LoteDao {
    void insert(Lote lote);
    void update(Lote lote);
    void deleteBynumero_pedido(int numero_pedido, String destino);
    Lote findByNumeroPedidoAndDestino(int numeroPedido, String destino);
    List<Lote> findAll();
    List<Lote> findLotesByPedidoStatus(String status); // <-- Adicione isso!
    void updateStatusPedido(int numeroPedido, String novoStatus); // se quiser expor esse método
}

