package model.dao;

import model.entities.Usuario;

import java.util.List;

public interface UsuarioDao {

    void insert(Usuario usuario);

    void update(Usuario usuario);

    void deleteById(int id);

    Usuario findById(int id);

    List<Usuario> findAll();

    Usuario findByUsuarioAndSenha(int usuario, String senha);
// ou, se o login for String:
// Usuario findByUsuarioAndSenha(String usuario, String senha);
}