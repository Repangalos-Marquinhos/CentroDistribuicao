// ProdutoDaoJDBC.java
package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.ProdutoDao;
import model.entities.Produto;
import model.entities.Secao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoJDBC implements ProdutoDao {
    private final Connection conn;

    public ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Produto produto) {
        PreparedStatement st = null;
        try {
            // Inclua 'id_produto' na lista de colunas e adicione um placeholder para ele
            st = conn.prepareStatement(
                    "INSERT INTO produtos (id_produto, descricao, secao, data_armazenmento) VALUES (?, ?, ?, ?)"
            );
            st.setInt(1, produto.getId_produto()); // <-- FORNEÇA O ID MANUALMENTE
            st.setString(2, produto.getDescricao());
            st.setInt(3, produto.getSecao().getId_secao());
            st.setDate(4, new java.sql.Date(produto.getData_armazenamento().getTime()));
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao inserir produto: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Produto produto) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE produtos SET descricao = ?, secao = ?, data_armazenmento = ? WHERE id_produto = ?"
            );
            st.setString(1, produto.getDescricao());
            st.setInt(2, produto.getSecao().getId_secao());
            st.setDate(3, new java.sql.Date(produto.getData_armazenamento().getTime()));
            st.setInt(4, produto.getId_produto());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar produto: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(int id_produto) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM produtos WHERE id_produto = ?");
            st.setInt(1, id_produto);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar produto: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Produto findById(int id_produto) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT p.*, s.id_secao, s.descricao AS nome_secao FROM produtos p " +
                            "JOIN secao s ON p.secao = s.id_secao WHERE p.id_produto = ?"
            );
            st.setInt(1, id_produto);
            rs = st.executeQuery();

            if (rs.next()) {
                Secao secao = new Secao(rs.getInt("id_secao"), rs.getString("nome_secao"));
                return new Produto(rs.getInt("id_produto"), rs.getString("descricao"), secao, rs.getDate("data_armazenmento"));
            }
            return null;
        } catch (SQLException e) {
            throw new DbException("Erro ao buscar produto: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Produto> findByDescricao(String descricao) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM produtos WHERE LOWER(descricao) LIKE ?");
            st.setString(1, descricao);
            rs = st.executeQuery();
            List<Produto> list = new ArrayList<>();
            while (rs.next()) {
                Secao secao = new Secao(rs.getInt("secao"), rs.getString("descricao"));
                Produto produto = new Produto(rs.getInt("id_produto"), rs.getString("descricao"), secao, rs.getDate("data_armazenmento"));
                list.add(produto);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException("Erro ao achar o produto: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }


    @Override
    public List<Produto> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT p.*, s.id_secao, s.descricao AS nome_secao FROM produtos p JOIN secao s ON p.secao = s.id_secao");
            rs = st.executeQuery();
            List<Produto> list = new ArrayList<>();
            while (rs.next()) {
                Secao secao = new Secao(rs.getInt("id_secao"), rs.getString("nome_secao"));
                Produto produto = new Produto(rs.getInt("id_produto"), rs.getString("descricao"), secao, rs.getDate("data_armazenmento"));
                list.add(produto);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException("Erro ao listar produtos: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Produto> findBySecaoId(int secaoId) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT p.*, s.id_secao, s.descricao AS nome_secao FROM produtos p JOIN secao s ON p.secao = s.id_secao WHERE s.id_secao = ?");
            st.setInt(1, secaoId);
            rs = st.executeQuery();
            List<Produto> list = new ArrayList<>();
            while (rs.next()) {
                Secao secao = new Secao(rs.getInt("id_secao"), rs.getString("nome_secao"));
                Produto produto = new Produto(rs.getInt("id_produto"), rs.getString("descricao"), secao, rs.getDate("data_armazenmento"));
                list.add(produto);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException("Erro ao listar produtos por seção: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}