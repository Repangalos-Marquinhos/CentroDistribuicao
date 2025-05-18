package model.dao;

import model.entities.Cidade;

import java.util.List;

public interface CidadeDao {

    void insert(Cidade cidade);

    void update(Cidade cidade);

    void deleteById(int id);

    Cidade findById(int id);

    List<Cidade> findAll();
}
