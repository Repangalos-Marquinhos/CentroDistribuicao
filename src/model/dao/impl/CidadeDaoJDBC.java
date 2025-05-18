package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.CidadeDao;
import model.entities.Cidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CidadeDaoJDBC implements CidadeDao {

    private Connection conn;

    public CidadeDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Cidade cidade) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO cidade (cidade) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, cidade.getCidade());
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    cidade.setId(rs.getInt(1));
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
    public void update(Cidade cidade) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE cidade SET cidade = ? WHERE id_cidade = ?");
            st.setString(1, cidade.getCidade());
            st.setInt(2, cidade.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar cidade: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(int id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM cidade WHERE id_cidade = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar cidade: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Cidade findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM cidade WHERE id_cidade = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                return instantiateCidade(rs);
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
    public List<Cidade> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM cidade");
            rs = st.executeQuery();

            List<Cidade> list = new ArrayList<>();
            while (rs.next()) {
                list.add(instantiateCidade(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Cidade instantiateCidade(ResultSet rs) throws SQLException {
        return new Cidade(
                rs.getInt("id_cidade"),
                rs.getString("cidade")
        );
    }
}
