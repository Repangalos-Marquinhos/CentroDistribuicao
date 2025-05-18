/*package application;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcao = 0;
        String controle = "";

        //Login do usuário
        while (true) {

            System.out.println("----------- Login -----------");
            System.out.print("Digite seu usuário: ");
            String usuario_input = sc.nextLine();

            System.out.println("Digite sua senha: ");
            String senha_input = sc.nextLine();

            if (usuario_input.equals(adm.getUsuario()) && senha_input.equals(adm.getSenha())) {
                System.out.println("Login realizado com sucesso!\n");
                controle = "admin";
                break;
            } else if (usuario_input.equals(user.getUsuario()) && senha_input.equals(user.getSenha())) {
                System.out.println("Login realizado com sucesso!\n");
                controle = "user";
                break;
            } else {
                System.out.println("Usuário ou senha inválidos. Tente novamente.\n");
            }

        }
        do {

            // Controle para exibir o menu correto

            if (controle.equals("admin")) {
                Utilidades.exibirMenuADM();
            } else if (controle.equals("user")) {
                Utilidades.exibirMenuUSER();
            }

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    Utilidades.cadastrarProduto(sc, listaItens);
                    break;
                case 2:
                    Utilidades.retirarProduto(sc, listaItens);
                    break;
                case 3:
                    Utilidades.listarProdutos(listaItens);
                    break;
                case 4:
                    Utilidades.criarPedido(sc, listaItens, listaPedidos);
                    break;
                case 5:
                    Utilidades.listarPedidos(listaItens, listaPedidos);
                    break;
                case 6:
                    Utilidades.calcularDistancia(sc);
                    break;
                case 7:
                    // Utilidades.volumeGalpao(sc);
                    break;
                case 8:
                    Utilidades.atualizarStatus(sc, listaPedidos);
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
    }
}
*/


