package model.dao;

import model.entities.Pedido;

import java.util.List;

public interface PedidoDao {

    void insert(Pedido pedido);

    void update(Pedido pedido);

    void updateStatusPedido(int numeroPedido, String novoStatus);

    List<Pedido> findByStatus(String status);

    void deleteById(int id);

    Pedido findById(int id);

    List<Pedido> findAll();

    List<Pedido> findByUsuarioId(int usuarioId);  // útil para listagem de pedidos por usuário
}