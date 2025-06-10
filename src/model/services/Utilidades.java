package model.services;

import db.DB;
import model.dao.LoteDao;
import model.dao.PedidoDao;
import model.dao.ProdutoDao;
import model.dao.UsuarioDao;
import model.dao.impl.DaoFactory;
import model.dao.impl.PedidoDaoJDBC;
import model.entities.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Utilidades {

    private static final Connection conn = DB.getConnection();
    private static final ProdutoDao produtoDao = DaoFactory.createProdutoDao();
    private static final PedidoDao pedidoDao = DaoFactory.createPedidoDao();
    private static final UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
    private static final LoteDao loteDao = DaoFactory.createLoteDao();
    private static final Scanner sc = new Scanner(System.in);

    public static void exibirMenuADM() {
        System.out.println("+---------------------------------+");
        System.out.println("|   Selecione a opção desejada    |");
        System.out.println("|---------------------------------|");
        System.out.println("| 0- Cadastrar Usuário            |");
        System.out.println("| 1- Cadastrar Produto            |");
        System.out.println("| 2- Retirar Produto              |");
        System.out.println("| 3- Listar Produtos              |");
        System.out.println("| 4- Criar Pedido                 |");
        System.out.println("| 5- Listar Pedidos               |");
        System.out.println("| 6- Calcular Distância          |");
        System.out.println("| 7- Volume Galpao               |");
        System.out.println("| 8- Atualizar Status            |");
        System.out.println("| 9- Sair                        |");
        System.out.println("+---------------------------------+");
    }

    public static void exibirMenuUSER() {
        System.out.println("+---------------------------------+");
        System.out.println("|   Selecione a opção desejada    |");
        System.out.println("|---------------------------------|");
        System.out.println("| 1- Cadastrar Produto            |");
        System.out.println("| 2- Retirar Produto              |");
        System.out.println("| 3- Listar Produtos              |");
        System.out.println("| 4- Criar Pedido                 |");
        System.out.println("| 5- Listar Pedidos               |");
        System.out.println("| 6- Calcular Distância          |");
        System.out.println("| 7- Volume Galpao               |");
        System.out.println("| 8- Sair                        |");
        System.out.println("+---------------------------------+");
    }

    public static void cadastrarUsuario(String senha, int id, String permissao) {
        try {

            Usuario usuario = new Usuario(senha, id, permissao);
            usuarioDao.insert(usuario);


            System.out.println("Usuario cadastrado com sucesso!\n");

        }catch (Exception e){
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }

    }

    public static void cadastrarProduto(String descricao, Date data, int idSecao) {
        try {

            Secao secao = new Secao(idSecao, null); // Só o ID é necessário para inserção
            Produto produto = new Produto(9, descricao, secao, data);

            produtoDao.insert(produto);
            System.out.println("Produto cadastrado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    public static void cadastrarPedido(int numeroPedido, int idUsuario, Date dataPedido) {
        try {
            // Cria o objeto Usuario apenas com o ID (a senha não é necessária para inserir o pedido)
            Usuario usuario = new Usuario();
            usuario.setId(idUsuario);

            // Cria o objeto Pedido
            Pedido pedido = new Pedido();
            pedido.setId(numeroPedido);
            pedido.setId_usuario(usuario);
            pedido.setData_pedido(dataPedido);

            // Cria o DAO e insere o pedido
            pedidoDao.insert(pedido);

            System.out.println("Pedido cadastrado com sucesso!\n");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar pedido: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public static void cadastrarLote(
            int idProduto,
            String destino,
            int idCidade,
            int idEstado,
            String cep,
            int idPedido
    ) {
        try {
            // Instancie os objetos necessários apenas com o ID (ou busque do banco, se preferir)
            Produto produto = new Produto();
            produto.setId_produto(idProduto);

            Cidade cidade = new Cidade();
            cidade.setId(idCidade);

            Estado estado = new Estado();
            estado.setId_estado(idEstado);

            Pedido pedido = new Pedido();
            pedido.setId(idPedido);

            // Crie o lote
            Lote lote = new Lote(produto, destino, cidade, estado, cep, pedido);

            // Insira usando o DAO
            loteDao.insert(lote);

            System.out.println("Lote cadastrado com sucesso!\n");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar lote: " + e.getMessage());
        }
    }

    public static void excluirLote(int id, String destino){

        //Lote lote = loteDao.findByNumeroPedidoAndDestino(id, destino);
        //if (lote == null) {
         //   System.out.println("Lote não encontrado.\n");
        //} else {
            loteDao.deleteBynumero_pedido(id, destino);
            System.out.println("Lote excluído com sucesso!\n");
        //}

    }

    public static void excluirProduto(int id){
        produtoDao.deleteById(id);
        System.out.println("Produto excluído com sucesso!\n");
    }


    public static void retirarProduto(Scanner sc) {
        System.out.print("Digite o ID do produto que deseja retirar: ");
        int id = sc.nextInt();

        Produto produto = produtoDao.findById(id);
        if (produto == null) {
            System.out.println("Produto não encontrado.\n");
        } else {
            produtoDao.deleteById(id);
            System.out.println("Produto removido com sucesso!\n");
        }
    }

    public static void listarProdutos() {
        List<Produto> produtos = produtoDao.findAll();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.\n");
        } else {
            for (Produto p : produtos) {
                System.out.println("--------------------------------------------------");
                System.out.println(p);
                System.out.println("--------------------------------------------------");
            }
        }
    }

    public static void criarPedido(int idProduto, String endereco, String cep, String nomeCidade, String siglaEstado, int numeroLote) {
        try {
            System.out.println("----------- Criar Pedido -----------");

            System.out.print("Id do pedido: ");
            //sc.nextLine();
            int id_pedido = sc.nextInt();

            System.out.print("Id do usuario: ");
            sc.nextLine();
            String id_user = sc.nextLine();

            System.out.print("Data de entrega (yyyy-mm-dd): ");
            String dataStr = sc.nextLine();
            Date dataEntrega = Date.valueOf(dataStr);

            Pedido pedido = new Pedido(dataEntrega, id_pedido , new Usuario("12345", 54, "admin"));
            pedidoDao.insert(pedido);
            System.out.println("Pedido criado com sucesso!\n");

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Verifique os dados e tente novamente.\n");
        } catch (Exception e) {
            System.out.println("Erro ao criar pedido: " + e.getMessage());
        }
    }

    public static void listarPedidos() {
        List<Pedido> pedidos = pedidoDao.findAll();
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.\n");
        } else {
            for (Pedido p : pedidos) {
                System.out.println("--------------------------------------------------");
                System.out.println("ID do Pedido: " + p.getId());
                System.out.println("Id usuario: " + p.getId_usuario());
                System.out.println("Data de Entrega: " + p.getData_pedido());
                System.out.println("--------------------------------------------------");
            }
        }
    }

    public static void calcularDistancia(Scanner sc) {
        System.out.print("Digite o endereço de destino: ");
        //sc.nextLine(); // limpar buffer
        String destino = sc.nextLine();
        String resultado = GoogleMapsService.calcularDistancia(destino);
        System.out.println("\n-------- Resultado da Distância ------------------");
        System.out.println("|   " + resultado + "   |");
        System.out.println("--------------------------------------------------\n");
    }

    public static void volumeGalpao(Scanner sc) {
        System.out.print("Digite a largura do galpão: ");
        int largura = sc.nextInt();
        System.out.print("Digite o comprimento do galpão: ");
        int comprimento = sc.nextInt();
        System.out.print("Digite a altura do galpão: ");
        int altura = sc.nextInt();

        int volume = largura * comprimento * altura;

        System.out.println("O volume do galpão é: " + volume + " m³");
    }
}