package application;

import db.DB;
import model.dao.*;
import model.dao.impl.*;
import model.entities.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import static model.services.Utilidades.calcularDistancia;

public class Program2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
/*
        Connection conn = DB.getConnection();

        testSecaoDao(conn);
        testProdutoDao(conn);
        testPedidoDao(conn);
        testLoteDao(conn);
        testCidadeDao(conn);
        testEstadoDao(conn);
        testUsuarioDao(conn);

        DB.closeConnection();

*/

        calcularDistancia(sc);
    }

    private static void testSecaoDao(Connection conn) {
        System.out.println("===== SecaoDao Tests =====");
        SecaoDao secaoDao = new SecaoDaoJDBC(conn);

        Secao secao = new Secao(3233546, "Eletrônicos");

        secaoDao.insert(secao);
        System.out.println("Inserted: " + secao);

        secao.setDescricao("Eletrônicos Atualizados");
        secaoDao.update(secao);
        System.out.println("Updated: " + secao);

        Secao foundSecao = secaoDao.findById(secao.getId_secao());
        System.out.println("Found: " + foundSecao);

        List<Secao> secoes = secaoDao.findAll();
        secoes.forEach(System.out::println);

        // secaoDao.deleteById(secao.getId_secao());
    }

    private static void testProdutoDao(Connection conn) {
        System.out.println("===== ProdutoDao Tests =====");
        Secao secao = new Secao(1, "Eletrônicos Atualizados");
        ProdutoDao produtoDao = new ProdutoDaoJDBC(conn);

        Produto produto = new Produto(123454, "Celular", secao, new Date(System.currentTimeMillis()));

        produtoDao.insert(produto);
        System.out.println("Inserted: " + produto);

        produto.setDescricao("Celular Atualizado");
        produtoDao.update(produto);
        System.out.println("Updated: " + produto);

        Produto foundProduto = produtoDao.findById(produto.getId_produto());
        System.out.println("Found: " + foundProduto);

        List<Produto> produtos = produtoDao.findAll();
        produtos.forEach(System.out::println);

        List<Produto> produtosBySecao = produtoDao.findBySecaoId(secao.getId_secao());
        produtosBySecao.forEach(System.out::println);

        // produtoDao.deleteById(produto.getId_produto());
    }

    private static void testPedidoDao(Connection conn) {
        System.out.println("===== PedidoDao Tests =====");
        Usuario usuario = new Usuario("12345", 1);
        PedidoDao pedidoDao = new PedidoDaoJDBC(conn);

        Pedido pedido = new Pedido(new Date(System.currentTimeMillis()), 66767, new Usuario("12345", 54));

        pedidoDao.insert(pedido);
        System.out.println("Inserted: " + pedido);

        pedido.setData_pedido(new Date(System.currentTimeMillis()));
        pedidoDao.update(pedido);
        System.out.println("Updated: " + pedido);

        Pedido foundPedido = pedidoDao.findById(pedido.getId());
        System.out.println("Found: " + foundPedido);

        List<Pedido> pedidos = pedidoDao.findAll();
        pedidos.forEach(System.out::println);

        List<Pedido> pedidosByUser = pedidoDao.findByUsuarioId(usuario.getId());
        pedidosByUser.forEach(System.out::println);

        // pedidoDao.deleteById(pedido.getId());
    }

    private static void testLoteDao(Connection conn) {
        System.out.println("===== LoteDao Tests =====");
        Produto produto = new Produto(1, "Celular Atualizado", new Secao(1, "Eletrônicos Atualizados"), new Date(System.currentTimeMillis()));
        Pedido pedido = new Pedido(new Date(System.currentTimeMillis()), 1, new Usuario("12345", 54));
        Cidade cidade = new Cidade(1, "São Paulo");
        Estado estado = new Estado(1, "São Paulo", "SP");
        LoteDao loteDao = new LoteDaoJDBC(conn);
        Lote lote = new Lote(produto, "Rua A", cidade, estado, "12345-678", pedido);
        loteDao.insert(lote);
        System.out.println("Inserted: " + lote);

        lote.setDestino("Rua B");
        loteDao.update(lote);
        System.out.println("Updated: " + lote);

        Lote foundLote = loteDao.findByProdutoId(produto.getId_produto());
        System.out.println("Found: " + foundLote);

        List<Lote> lotes = loteDao.findAll();
        lotes.forEach(System.out::println);

        // loteDao.deleteByProdutoId(produto.getId_produto());
    }

    private static void testCidadeDao(Connection conn) {
        System.out.println("===== CidadeDao Tests =====");
        CidadeDao cidadeDao = new CidadeDaoJDBC(conn);
        List<Cidade> cidades = cidadeDao.findAll();
        cidades.forEach(System.out::println);
    }

    private static void testEstadoDao(Connection conn) {
        System.out.println("===== EstadoDao Tests =====");
        EstadoDao estadoDao = new EstadoDaoJDBC(conn);
        List<Estado> estados = estadoDao.findAll();
        estados.forEach(System.out::println);
    }

    private static void testUsuarioDao(Connection conn) {
        System.out.println("===== UsuarioDao Tests =====");
        UsuarioDao usuarioDao = new UsuarioDaoJDBC(conn);
        List<Usuario> usuarios = usuarioDao.findAll();
        usuarios.forEach(System.out::println);
    }
}
