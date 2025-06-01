package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.EstadoDao;
import model.entities.Estado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoDaoJDBC implements EstadoDao {

    private Connection conn;

    public EstadoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Estado estado) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO estado (UF, sigla) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, estado.getEstado()); // ou getNome() dependendo do nome do campo na sua classe
            st.setString(2, estado.getSigla());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    estado.setId_estado(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Estado estado) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE estado SET UF = ?, sigla = ? WHERE id_estado = ?"
            );
            st.setString(1, estado.getEstado()); // ou getNome()
            st.setString(2, estado.getSigla());
            st.setInt(3, estado.getId_estado());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar estado: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(int id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM estado WHERE id_estado = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar estado: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Estado findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM estado WHERE id_estado = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                return instantiateEstado(rs);
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Estado> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM estado");
            rs = st.executeQuery();

            List<Estado> list = new ArrayList<>();
            while (rs.next()) {
                list.add(instantiateEstado(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Estado instantiateEstado(ResultSet rs) throws SQLException {
        return new Estado(
                rs.getInt("id_estado"),
                rs.getString("UF"),        // <- aqui está o ajuste
                rs.getString("sigla")
        );
    }
}