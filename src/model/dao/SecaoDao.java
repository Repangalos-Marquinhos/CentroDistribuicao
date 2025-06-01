package model.dao;

import model.entities.Secao;
import java.util.List;

public interface SecaoDao {

    void insert(Secao secao);

    void update(Secao secao);

    void deleteById(int id_secao);

    Secao findById(int id_secao);

    List<Secao> findAll();
}