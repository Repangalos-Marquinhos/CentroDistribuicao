package model.dao;

import model.entities.Produto;
import java.util.List;

public interface ProdutoDao {

    void insert(Produto produto);

    void update(Produto produto);

    void deleteById(int id_produto);

    Produto findById(int id_produto);

    List<Produto> findAll();

    List<Produto> findBySecaoId(int secaoId); // útil para listar produtos por seção
}
