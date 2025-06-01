package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SecaoDao;
import model.entities.Secao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SecaoDaoJDBC implements SecaoDao {

    private Connection conn;

    public SecaoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Secao secao) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO secao (id_secao, descricao) VALUES (?,?)"
            );
            st.setInt(1, secao.getId_secao());
            st.setString(2, secao.getDescricao());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao inserir seção: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Secao secao) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE secao SET descricao = ? WHERE id_secao = ?"
            );
            st.setString(1, secao.getDescricao());
            st.setInt(2, secao.getId_secao());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar seção: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(int id_secao) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM secao WHERE id_secao = ?");
            st.setInt(1, id_secao);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar seção: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Secao findById(int id_secao) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM secao WHERE id_secao = ?");
            st.setInt(1, id_secao);
            rs = st.executeQuery();
            if (rs.next()) {
                return new Secao(rs.getInt("id_secao"), rs.getString("descricao"));
            }
            return null;
        } catch (SQLException e) {
            throw new DbException("Erro ao buscar seção: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Secao> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM secao");
            rs = st.executeQuery();

            List<Secao> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Secao(rs.getInt("id_secao"), rs.getString("descricao")));
            }
            return list;
        } catch (SQLException e) {
            throw new DbException("Erro ao listar seções: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}