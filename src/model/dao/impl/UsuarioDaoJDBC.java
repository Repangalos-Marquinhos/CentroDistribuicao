package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.UsuarioDao;
import model.entities.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoJDBC implements UsuarioDao {

    private Connection conn;

    public UsuarioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    public void insert(Usuario usuario) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO usuario (id_user, senha) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, usuario.getUsuario());
            st.setString(2, usuario.getSenha());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    usuario.setId(id);
                }
                DB.closeResultSet(rs);
            }else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }

    }

    public void update(Usuario usuario) {
        String sql = "UPDATE usuario SET id_user = ?, senha = ? WHERE id_user = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, usuario.getUsuario());
            st.setString(2, usuario.getSenha());
            st.setInt(3, usuario.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage(), e);
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM usuario WHERE id_user = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar usuário: " + e.getMessage(), e);
        }
    }

    public Usuario findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM usuario WHERE id_user = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_user"));
                usuario.setUsuario(rs.getInt("id_user")); // Ajuste conforme necessário
                usuario.setSenha(rs.getString("senha"));
                return usuario;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }


    public List<Usuario> findAll() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> list = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                list.add(instantiateUsuario(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os usuários: " + e.getMessage(), e);
        }
        return list;
    }

    private Usuario instantiateUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getString("senha"),
                rs.getInt("id_user")
        );
    }
}
