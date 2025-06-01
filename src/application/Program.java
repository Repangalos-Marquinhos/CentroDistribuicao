package application;

import db.DB;
import model.dao.impl.DaoFactory;
import model.dao.PedidoDao;
import model.dao.ProdutoDao;
import model.dao.UsuarioDao;
import model.entities.Usuario;
import model.services.Utilidades;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
        ProdutoDao produtoDao = DaoFactory.createProdutoDao();
        PedidoDao pedidoDao = DaoFactory.createPedidoDao();

        Usuario usuarioLogado = null;

        // Login do usuário
        while (true) {
            System.out.println("----------- Login -----------");
            System.out.print("Digite seu usuário: ");
            int usuario_input = sc.nextInt();

            System.out.print("Digite sua senha: ");
            String senha_input = sc.nextLine();

            usuarioLogado = usuarioDao.findById(usuario_input);

            if (usuarioLogado != null) {
                System.out.println("Login realizado com sucesso!\n");
                break;
            } else {
                System.out.println("Usuário ou senha inválidos. Tente novamente.\n");
            }
        }

        boolean isAdmin = true;

        int opcao;
        do {
            if (isAdmin) {
                Utilidades.exibirMenuADM();
            } else {
                Utilidades.exibirMenuUSER();
            }

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 0:
                   // if (isAdmin) Utilidades.cadastrarUsuario(sc);
                    break;
                case 1:
                    //Utilidades.cadastrarProduto(sc);
                    break;
                case 2:
                    Utilidades.retirarProduto(sc);
                    break;
                case 3:
                    Utilidades.listarProdutos();
                    break;
                case 4:
                    //Utilidades.criarPedido(sc);
                    break;
                case 5:
                    Utilidades.listarPedidos();
                    break;
                case 6:
                    Utilidades.calcularDistancia(sc);
                    break;
                case 7:
                    Utilidades.volumeGalpao(sc); // Se implementado no futuro
                    break;
                case 8:

                    break;
                case 9:
                    System.out.println("Saindo\n");
                    break;
                default:
                    System.out.println("Opção inválida\n");
                    break;
            }

        } while (opcao != 9);

        sc.close();
        DB.closeConnection();
    }
}
