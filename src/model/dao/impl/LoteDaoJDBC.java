package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.LoteDao;
import model.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoteDaoJDBC implements LoteDao {

    private Connection conn;

    public LoteDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Lote lote) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO lote (id_produto, destino, id_cidade, id_estado, cep, id_pedido) " +
                            "VALUES (?, ?, ?, ?, ?, ?)"
            );

            st.setInt(1, lote.getProduto().getId_produto());
            st.setString(2, lote.getDestino());
            st.setInt(3, lote.getCidade().getId());
            st.setInt(4, lote.getEstado().getId_estado());
            st.setString(5, lote.getCep());
            st.setInt(6, lote.getPedido().getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Erro ao inserir lote: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Lote lote) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE lote SET destino = ?, id_cidade = ?, id_estado = ?, cep = ? " +
                            "WHERE id_produto = ? AND id_pedido = ?"
            );

            st.setString(1, lote.getDestino());
            st.setInt(2, lote.getCidade().getId());
            st.setInt(3, lote.getEstado().getId_estado());
            st.setString(4, lote.getCep());
            st.setInt(5, lote.getProduto().getId_produto());
            st.setInt(6, lote.getPedido().getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar lote: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteByProdutoId(int produtoId) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM lote WHERE id_produto = ?"
            );

            st.setInt(1, produtoId);


            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Erro ao deletar lote: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Lote findByProdutoId(int produtoId){
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT l.*, p.descricao, p.data_armazenamento, s.id_secao, "
                    +"s.descricao AS nome_secao, c.id_cidade, c.cidade, e.id_estado, "
                    +"e.UF AS estado, e.sigla, pd.numero_pedido AS id_pedido, pd.data_pedido, "
                    +"u.id_user AS id_usuario, u.senha AS senha_usuario "
                    +"FROM lote l JOIN produtos p ON l.id_produto = p.id_produto "
                    +"JOIN secao s ON p.secao = s.id_secao "
                    +"JOIN cidade c ON l.cidade = c.id_cidade "
                    +"JOIN estado e ON l.estado = e.id_estado "
                    +"JOIN pedido pd ON l.numero_pedido = pd.numero_pedido "
                    +"JOIN usuario u ON pd.id_user = u.id_user "
                    +"WHERE l.id_produto = ?;"
           );

            st.setInt(1, produtoId);

            rs = st.executeQuery();

            if (rs.next()) {
                return instantiateLote(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new DbException("Erro ao buscar lote: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Lote> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT l.*, p.descriçao, p.data_armazenamento, s.id_secao, s.nome AS nome_secao, " +
                            "c.id_cidade, c.cidade, e.id_estado, e.estado, e.sigla, " +
                            "pd.id AS id_pedido, pd.data_pedido, u.id AS id_usuario, u.nome AS nome_usuario " +
                            "FROM lote l " +
                            "JOIN produto p ON l.id_produto = p.id_produto " +
                            "JOIN secao s ON p.id_secao = s.id_secao " +
                            "JOIN cidade c ON l.id_cidade = c.id_cidade " +
                            "JOIN estado e ON l.id_estado = e.id_estado " +
                            "JOIN pedido pd ON l.id_pedido = pd.id " +
                            "JOIN usuario u ON pd.id_usuario = u.id"
            );

            rs = st.executeQuery();

            List<Lote> list = new ArrayList<>();
            while (rs.next()) {
                list.add(instantiateLote(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new DbException("Erro ao listar lotes: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Lote instantiateLote(ResultSet rs) throws SQLException {
        Secao secao = new Secao();
        secao.setId_secao(rs.getInt("id_secao"));
        secao.setDescricao(rs.getString("descricao"));

        Produto produto = new Produto(
                rs.getInt("id_produto"),
                rs.getString("descriçao"),
                secao,
                rs.getDate("data_armazenamento")
        );

        Cidade cidade = new Cidade(rs.getInt("id_cidade"), rs.getString("cidade"));

        Estado estado = new Estado(
                rs.getInt("id_estado"),
                rs.getString("estado"),
                rs.getString("sigla")
        );

        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id_user"));
        usuario.setSenha(rs.getString("senha"));

        Pedido pedido = new Pedido(
                rs.getDate("data_pedido"),
                rs.getInt("id_pedido"),
                usuario
        );

        return new Lote(produto, rs.getString("destino"), cidade, estado, rs.getString("cep"), pedido);
    }
}
