package model.dao;

import model.entities.Estado;
import java.util.List;

public interface EstadoDao {

    void insert(Estado estado);

    void update(Estado estado);

    void deleteById(int id_estado);

    Estado findById(int id_estado);

    List<Estado> findAll();
}
