package model.dao;

import model.entities.Lote;

import java.util.List;

public interface LoteDao {

    void insert(Lote lote);

    void update(Lote lote);

    void deleteByProdutoId(int produtoId);  // Exemplo de chave primária alternativa

    Lote findByProdutoId(int produtoId); // Supondo que Produto seja chave em Lote

    List<Lote> findAll();
}