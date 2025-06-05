package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.PedidoDao;
import model.entities.Pedido;
import model.entities.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDaoJDBC implements PedidoDao {

    private final Connection conn;

    public PedidoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Pedido pedido) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO pedido (numero_pedido, id_user, data_pedido) VALUES (?, ?, ?)"
            );
            st.setInt(1, pedido.getId());
            st.setInt(2, pedido.getId_usuario().getId());
            st.setDate(3, new java.sql.Date(pedido.getData_pedido().getTime()));
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao inserir pedido: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Pedido pedido) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE pedido SET id_user = ?, data_pedido = ? WHERE numero_pedido = ?"
            );
            st.setInt(1, pedido.getId_usuario().getId());
            st.setDate(2, new java.sql.Date(pedido.getData_pedido().getTime()));
            st.setInt(3, pedido.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar pedido: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public void updateStatusPedido(int numeroPedido, String novoStatus) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE pedido SET status = ? WHERE numero_pedido = ?"
            );
            st.setString(1, novoStatus);
            st.setInt(2, numeroPedido);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar status do pedido: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(int id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM pedido WHERE numero_pedido = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar pedido: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public List<Pedido> findByStatus(String status) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM pedido WHERE status = ?"
            );
            st.setString(1, status);
            rs = st.executeQuery();

            List<Pedido> list = new ArrayList<>();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("numero_pedido"));
                pedido.setData_pedido(rs.getDate("data_pedido"));
                pedido.setStatus(rs.getString("status"));
                // Adicione outros campos conforme necessário
                list.add(pedido);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pedidos por status: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (st != null) st.close(); } catch (SQLException ignored) {}
        }
    }

    @Override
    public Pedido findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT p.*, u.id_user, u.senha FROM pedido p " +
                            "JOIN usuario u ON p.id_user = u.id_user WHERE numero_pedido = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario(rs.getString("senha"), rs.getInt("id_user"), rs.getString("permissao"));
                return new Pedido(rs.getDate("data_pedido"), rs.getInt("numero_pedido"), usuario);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException("Erro ao buscar pedido: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Pedido> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT p.*, u.id_user, u.senha FROM pedido p " +
                            "JOIN usuario u ON p.id_user = u.id_user"
            );
            rs = st.executeQuery();

            List<Pedido> list = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getString("senha"), rs.getInt("id_user"), rs.getString("permissao") );
                Pedido pedido = new Pedido(rs.getDate("data_pedido"), rs.getInt("numero_pedido"), usuario);
                list.add(pedido);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException("Erro ao listar pedidos: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Pedido> findByUsuarioId(int usuarioId) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT p.*, u.id_user, u.senha FROM pedido p " +
                            "JOIN usuario u ON p.id_user = u.id_user WHERE u.id_user = ?"
            );
            st.setInt(1, usuarioId);
            rs = st.executeQuery();

            List<Pedido> list = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getString("senha"),rs.getInt("id_user"), rs.getString("permissao"));
                Pedido pedido = new Pedido(rs.getDate("data_pedido"), rs.getInt("numero_pedido"), usuario);
                list.add(pedido);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException("Erro ao listar pedidos por usuário: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}