package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.LoteDao;
import model.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoteDaoJDBC implements LoteDao {

    private final Connection conn;

    public LoteDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Lote lote) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO lote (id_produto, destino, cidade, estado, cep, numero_pedido) " +
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
                    "UPDATE lote SET destino = ?, cidade = ?, estado = ?, cep = ? " +
                            "WHERE id_produto = ? AND numero_pedido = ?"
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
    public void deleteBynumero_pedido(int numero_pedido, String destino) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM lote WHERE numero_pedido = ? AND destino = ?"
            );
            st.setInt(1, numero_pedido);
            st.setString(2, destino);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao buscar lote: " + e.getMessage());
        } finally {

            DB.closeStatement(st);
        }
    }

    public Lote findByNumeroPedidoAndDestino(int numeroPedido, String destino) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM lote WHERE numero_pedido = ? AND destino = ?"
            );
            st.setInt(1, numeroPedido);
            st.setString(2, destino);
            rs = st.executeQuery();
            if (rs.next()) {
                // Aqui você pode usar seu método instantiateLote, adaptando se necessário
                // Exemplo simplificado:
                // return instantiateLote(rs);
                // Ou crie um Lote básico:
                Lote lote = new Lote();
                lote.setDestino(destino);
                Pedido pedido = new Pedido();
                pedido.setId(numeroPedido);
                lote.setPedido(pedido);
                return lote;
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
                    "SELECT ped.numero_pedido, p.descricao, "
                            +"p.data_armazenmento, s.id_secao, "
                            +"s.descricao AS nome_secao, l.destino, "
                            +"ped.data_pedido "
                            +"FROM lote l "
                            +"JOIN produtos p ON l.id_produto = p.id_produto "
                            +"JOIN secao s ON p.secao = s.id_secao "
                            +"JOIN pedido ped ON l.numero_pedido = ped.numero_pedido;"
            );

            rs = st.executeQuery();
            List<Lote> list = new ArrayList<>();
            while (rs.next()) {
                Secao secao = new Secao(rs.getInt("id_secao"), rs.getString("nome_secao"));
                Produto produto = new Produto(1,
                        rs.getString("descricao"),
                        secao,
                        rs.getDate("data_armazenmento")
                );

                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("numero_pedido"));
                pedido.setData_pedido(rs.getDate("data_pedido"));

// ou use o construtor se tiver um

                Lote lote = new Lote(produto, rs.getString("destino"), pedido);

                list.add(lote);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException("Erro ao listar lotes com produto e pedido: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }


    private Lote instantiateLote(ResultSet rs) throws SQLException {
        Secao secao = new Secao();
        secao.setId_secao(rs.getInt("id_secao"));
        secao.setDescricao(rs.getString("nome_secao"));

        Produto produto = new Produto(
                rs.getInt("id_produto"),
                rs.getString("descricao"),
                secao,
                rs.getDate("data_armazenmento")
        );

        Cidade cidade = new Cidade(rs.getInt("id_cidade"), rs.getString("cidade"));

        Estado estado = new Estado(
                rs.getInt("id_estado"),
                rs.getString("estado"),
                rs.getString("sigla")
        );

        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id_usuario"));
        usuario.setSenha(rs.getString("senha_usuario"));

        Pedido pedido = new Pedido(
                rs.getDate("data_pedido"),
                rs.getInt("id_pedido"),
                usuario
        );

        return new Lote(produto, rs.getString("destino"), cidade, estado, rs.getString("cep"), pedido);
    }
}